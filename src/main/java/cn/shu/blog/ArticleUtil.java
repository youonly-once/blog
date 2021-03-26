package cn.shu.blog;

import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.Category;
import cn.shu.blog.dao.ArticleMapper;
import cn.shu.blog.dao.CategoryMapper;
import cn.shu.blog.utils.*;
import cn.shu.blog.utils.lucene.LuceneIndexUntil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 舒新胜
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-21 20:34
 */
@Component
@Slf4j
public class ArticleUtil {

    @Resource
    private SpringBootJarUtil springBootJarUtil = null;
    /**
     * pdf转pdf工具路径
     */
    @Value("${swfToolsPath}")
    private String swfToolsPath = "D:/SWFTools/pdf2swf.exe";

    /**
     * 获取外部静态资源路径
     */
    @Value("${extStaticSourcesPath}")
    private String extStaticSourcesPath = null;

    /**
     * 文档目录
     */
    @Value("${docPath}")
    private String docPath = null;

    /**
     * 索引目录
     */
    @Value("${articleIndexPath}")
    private String articleIndexPath = null;


    /**
     * 资源文件根路径
     */
    private String rootPath = "";

    /**
     * 文章mapper
     */
    @Resource
    private ArticleMapper articleMapper = null;

    /**
     * 分类mapper
     */
    @Resource
    private CategoryMapper categoryMapper = null;

    /**
     * 文章对象列表
     */
    private List<Article> articleList = new ArrayList<>();

    @Test
    public void scanArticle()  {
        //rootPath = springBootJarUtil.getJarPath()+extStaticSourcesPath;
        //资源目录绝对路径
        try {
            rootPath = springBootJarUtil.getExtStaticSources();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return;
        }
        //1、创建文件对象
        createArticles(rootPath, docPath);
        //2、插入数据库
        insertArticles();

        //4、更新或创建索引
        articleList = articleMapper.getArticlesForIndex();
        LuceneIndexUntil.createIndex(articleList, rootPath + articleIndexPath,true);
    }

    /**
     * 保存到数据库

     */
    private void insertArticles() {
        for (Article article : articleList) {
            //添加分类，并获取分类id
            HashMap<String, Object> params = new HashMap<>();
            params.put("categoryName", article.getCategoryName());
            List<Category> categories = categoryMapper.selectByAll(params);
            if (categories.size() > 0) {
                article.setCategoryId(categories.get(0).getId());
            } else {
                Category category = new Category();
                category.setCategoryName(article.getCategoryName());
                category.setId(null);
                categoryMapper.insert(category);
                article.setCategoryId(category.getId());
            }
            //3、转换pdf
            if (".pdf".equals(article.getFileType())) {
                turnPdf(article.getSourceFilePath(),article.getTargetFilePath());
            }
        }
        //4、新增或更新文章信息
        if (!articleList.isEmpty()){
            articleMapper.batchInsertOrUpdate(articleList);
        }




    }

    /**
     * 遍历文档生成文档Bean对象
     *
     * @param path   资源目录
     * @param docDir 文件文件夹
     */
    private void createArticles(String path, String docDir) {
        //递归遍历path下所有文件
        File dir = new File(path + docDir);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                //保存文件信息到数据库
                try {
                    createArticleObject(file);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            } else if (file.isDirectory()) {
                //遍历子目录
                if (file.getAbsolutePath().endsWith("2020最新Git教程（2小时从入门到精通）")) {
                    continue;
                }
                createArticles(file.getAbsolutePath(), "/");
            }
        }
    }

    /**
     * 生成Article对象
     *
     * @param file 文件对象
     */
    private void createArticleObject(File file) {

        /*2020-04-22_后端_JAVA_常用设计模式
         *按_分隔 第一部分为创建日期[0]
         * 第一部分为category [1]
         * 第三部分为tag     [2]
         * 第四部分为标题 [4]
         */

        String filenameAndExt = file.getName();
        int pointLoc = filenameAndExt.lastIndexOf(".");
        //获取文件名 不包括扩展名
        String filename = filenameAndExt.substring(0, pointLoc);
        //获取扩展名
        String ext = filenameAndExt.substring(pointLoc + 1).trim();

        //分割
        String[] fileInfo = filename.split("_");

        //创建日期
        String createDate = fileInfo[0].trim();
        //标签
        String tag = fileInfo[2].trim();
        //long categoryId = insertCategoryName(tag);
        //文件类型
        String fileType = "." + ext;
        //标题
        String title = fileInfo[3].trim();
        //最后修改时间
        String updateDate = DateUtil.formatDate(new Date(file.lastModified()), "yyyy-MM-dd");
        //源文件目录
        String sourceFilePath = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(rootPath) + rootPath.length());
        //目标文件目录 如果是。docx或。doc转为 pfg
        String targetFilePath = "/" + ext + "/" + filename + fileType;
        if ("docx".equalsIgnoreCase(ext)
                || "doc".equalsIgnoreCase(ext)) {
            fileType = ".pdf";
            targetFilePath = "/pdf/" + MD5.md5(filename) + fileType;
        }

        //文档描述：读取文档内容部分内容
        String desc;
        if ("docx".equalsIgnoreCase(ext) || "doc".equalsIgnoreCase(ext)) {
            desc = readWord(rootPath + sourceFilePath).trim();
        } else {
            desc = HtmlUtil.readHtml(rootPath + sourceFilePath, 200).trim();
            targetFilePath = sourceFilePath;
        }

        //展示图 以tag为标记
        String imgPath = "images/articles/" + tag + ".jpg";

        Article article = new Article();
        article.setTitle(title);
        article.setCreateDate(DateUtil.stringToDate(createDate));
        article.setUpdateDate(DateUtil.stringToDate(createDate));
        // article.setCategoryId((int)categoryId);
        article.setCategoryName(tag);
        article.setFileType(fileType);
        article.setTargetFilePath(targetFilePath);
        article.setSourceFilePath(sourceFilePath);
        article.setDescription(desc);
        article.setImagePath(imgPath);
        article.setUserId(33);
        article.setVisitors(0);
        //没有日期的文章不需要添加到数据库
        if (article.getCreateDate() != null) {
            articleList.add(article);
        }

    }


    /**
     * 读取word文件内容
     *
     * @param path word文件路径
     * @return 读取内容
     */
    private String readWord(String path) {
        // TODO Auto-generated method stub
        StringBuilder content = new StringBuilder();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(path);
            XWPFDocument doc = new XWPFDocument(inStream);
            //获取所有段落
            List<XWPFParagraph> paras = doc.getParagraphs();
            //遍历段落

            for (XWPFParagraph para : paras) {
                //当前段落的属性
                //CTPPr pr = para.getCTP().getPPr();
                //获取段落文本
                if (content.length() > 200) {
                    break;
                }
                content.append(para.getText().trim()).append(",");

            }
            return content.substring(0, 200);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.info("获取word文档内容错误:" + e.getMessage());
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    /**
     * doc转odf
     *
     * @param pathDoc doc目录
     * @param pathPdf pdf目录
     * @return 转换状态 {@code true},{@code false}
     */
    public boolean turnPdf(String pathDoc, String pathPdf) {
        try {
            //jar内部资源存在SWF文件
            if (springBootJarUtil.sourceExists(pathPdf)) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            pathDoc = springBootJarUtil.getExtStaticSources() + pathDoc;
            pathPdf = springBootJarUtil.getExtStaticSources() + pathPdf;
            boolean mkdirs = new File(pathPdf).getParentFile().mkdirs();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        File file = new File(pathPdf);
        if (file.exists() && !file.isDirectory()) {
            return true;
        }
        log.info("源DOC文件:" + pathDoc);
        log.info("生成pdf文件:" + pathPdf);
        //docx转pdf
        int b = Doc2Pdf.turn(pathDoc, pathPdf);
        if (b != 0) {
            log.error("生成pdf失败");
            return false;
        }
        return true;
    }
}
