package cn.shu.blog.service;

import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.Comment;
import cn.shu.blog.beans.SearchArticle;
import cn.shu.blog.beans.User;
import cn.shu.blog.dao.ArticleDaoInter;
import cn.shu.blog.utils.*;
import cn.shu.blog.utils.lucene.LuceneIndexUntil;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

@Service("articleServiceInter") //Spring管理
@Scope(value = "singleton") //单例
@Lazy //懒加载
@Slf4j
public class ArticleService implements ArticleServiceInter {

    @Autowired(required = false)
    //自动注入，首先寻找 ArticleDaoInter类型的(包括实现了的类)，找到唯一注入，
    //没找到或找到多个则按id(articleDaoInter)查找,查找到则注入
    private ArticleDaoInter articleDaoInter = null;
    //索引保存位置
    private String savePath="/article_index";
    @PostConstruct  //初始化方法
    public void init() {

    }

    @PreDestroy  //销毁方法
    public void destroy() {

    }

    /**
     * 增加访问次数
     *
     * @param articleId 文章ID
     */
    @Transactional(rollbackFor = Throwable.class)
    public void addVisitRecord(String articleId) {
        if (StringUtil.isEmpty(articleId)) {
            return;
        }
        articleDaoInter.addVisitRecord(Integer.parseInt(articleId));
    }

    /**
     * 获取单个文章信息
     *
     * @param articleId 文章ID
     * @return 文章对象列表
     */
    public Article getSingleArticle(String articleId) {
        if (StringUtil.isEmpty(articleId)) {
            return null;
        }

        return articleDaoInter.getSingleArticle(Integer.parseInt(articleId));
    }

    /**
     * 获取热门文章
     *
     * @return
     */
    public List<Article> getHotArticles() {
        return articleDaoInter.getHotArticles();
    }

    /**
     * 获取文章数量
     *
     * @return
     * @throws SQLException
     */
    public int getArticlesCount(String categoryId) throws SQLException {
        if (StringUtil.isEmpty(categoryId)) {
            return articleDaoInter.getArticlesCount(-1);
        } else {
            return articleDaoInter.getArticlesCount(Integer.parseInt(categoryId));

        }

    }

    /**
     * 获取所有文章信息
     *
     * @param currPage 0
     *                 5
     *                 10
     * @return 查询
     * @throws SQLException
     */
    public List<Article> getArticles(int currPage, String categoryId) {
        if (currPage < 1) {
            return null;
        }
        //开始记录
        int recordFirst = ((currPage - 1) * 5);
        List<Article> articles = null;
        if (StringUtil.isEmpty(categoryId)) {//显示全部分类
            //-1为所有
            articles = articleDaoInter.getArticles(recordFirst, 5, -1);
        } else {
            articles = articleDaoInter.getArticles(recordFirst, 5, Integer.parseInt(categoryId));
        }
        if (articles != null && !articles.isEmpty()) {
            //下载文章封面图片
            downloadImg(articles);
        }
        return articles;
    }

    /**
     * 下载封面图片
     *
     * @param articles 文章列表
     */
    private void downloadImg(List<Article> articles) {
        new Thread(() -> {
            //获取不重复的所有图片
            HashSet<String> set = new HashSet<>();
            for (Article article : articles) {
                set.add(article.getImagePath());
            }

            for (String imgRelativePath : set) {
                //判断封面图片是否存在
                //图片绝对路径
                String imageAbsolutePath = null;
                String imgStr = null;
                String fileName = null;
                String fileNameAndExt;
                try {
                    //文章展示图片文件完整路径
                    imgStr = SpringBootJarUtil.getExtStaticSources() + imgRelativePath;
                    File imageFile= new File(imgStr);
                    //存放文件名，包括后缀
                    fileNameAndExt=imageFile.getName();
                    //文件名,没有后缀
                    fileName=fileNameAndExt.substring(0,fileNameAndExt.lastIndexOf("."));

                    //图片存放路径，不包括文件名
                    imageAbsolutePath = imageFile.getParent();
                    File imagePathFile = new File(imageAbsolutePath);
                    //保存图片的目录不存在
                    if (!imagePathFile .exists()) {
                        boolean mkdirs = imagePathFile .mkdirs();
                        //创建失败
                        if (!mkdirs) {
                            log.warn("图片保存目录创建失败：" + imageAbsolutePath);
                            return;
                        }
                    }
                    //内部和外部资源都不存在
                    if (!SpringBootJarUtil.sourceExists(File.separator + imgRelativePath) && !imageFile.exists()) {
                            //1代表下载一页，一页一般有30张图片
                            File imgFile = GetNetImage.getPictures(fileName, 20, imageAbsolutePath, fileNameAndExt);
                           log.info("图片下载成功：" + imgFile.getAbsolutePath());
                    }
                } catch (FileNotFoundException e) {
                    log.warn( e.getMessage());
                    e.printStackTrace();
                }

            }
        }).start();

    }

    /**
     * 获取与当前文章相关的推荐文章
     * 按当前浏览文章的标题，分词
     * 分词后在数据库查找包含 这几个词的标题对应的文章
     * 按出现的词语数量 排序
     *
     * @return 匹配度最高的5篇文章
     */
    public List<Article> getRecommendArticles(String currArticleId,String currArticleTitle) {
        //Article currArticle = getSingleArticle(currArticleId);
        if (StringUtil.isEmpty(currArticleTitle) || StringUtil.isEmpty(currArticleId)) {
            return null;
        }
        String[] fields = {"title"};
        try {
            //布尔查询子条件
            Query multiQuery = LuceneIndexUntil.newMultiQuery(fields, currArticleTitle);
            TermQuery termQuery = new TermQuery(new Term("id", currArticleId));

            //封装子条件
            BooleanClause booleanClause = new BooleanClause(multiQuery, BooleanClause.Occur.MUST);
            //推荐文章不包括当前文章
            BooleanClause booleanClause1 = new BooleanClause(termQuery, BooleanClause.Occur.MUST_NOT);

            Query query = LuceneIndexUntil.newBooleanQuery(booleanClause, booleanClause1);
            return LuceneIndexUntil.searchDocument(null,query, 1,10, SpringBootJarUtil.getExtStaticSources() + savePath,1).getArticles();
        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * @param articleId 文章id
     * @param currPage  马上显示第几页
     * @param pageCount 每页显示数量
     * @return 评论内容
     */
    @Override
    public List<Comment> getArticleComments(String articleId, String currPage, int pageCount) {
        if (StringUtil.isEmpty(articleId)) {
            return null;
        }
        if (StringUtil.isEmpty(currPage)) {
            return null;
        }

        if (Integer.parseInt(currPage) < 1 || pageCount < 1) {
            return null;
        }
        int beginRecord = ((Integer.parseInt(currPage) - 1) * pageCount);
        return articleDaoInter.getArticleComments(Integer.parseInt(articleId), beginRecord, pageCount);
    }

    /**
     * 用户评论
     *
     * @param comment 评论内容
     * @param user    评论用户
     * @return 提示信息
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String articlesComment(Comment comment, Object user) {

        if (StringUtil.isEmpty(comment.getComment())) {
            return "不能评论空内容";
        }
        //1代表游客，默认值;
        int userId = 1;
        if (user != null) {
            userId = ((User) user).getId();
        }
        //提交评论
        int b = articleDaoInter.addArticlesComment(comment.getComment(), comment.getArticle().getId(), userId, DateUtil.getCurrDateAndTime());
        if (b > 0) {
            return "success";
        } else {
            return "评论失败、系统错误";
        }

    }

    /**
     *
     * @param searchStr 查询关键字
     * @param currPage 当前第几页
     * @param pageNum 一页几条数据
     * @return
     */
    @Override
    public SearchArticle searchArticle(String searchStr, Integer currPage, Integer pageNum) {
        //根据title、content搜索
        String[] fields=new String[]{"title","description"};
        try {
            //查询条件
            Query query = LuceneIndexUntil.newMultiQuery(fields, searchStr);
            //查询10条
            SearchArticle searchArticle = LuceneIndexUntil
                    .searchDocument(searchStr,query, currPage, pageNum,SpringBootJarUtil.getExtStaticSources() + savePath,0);

            System.out.println("搜索结果:"+searchArticle.getArticles().size()+" "+searchArticle);

            return searchArticle;
        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
