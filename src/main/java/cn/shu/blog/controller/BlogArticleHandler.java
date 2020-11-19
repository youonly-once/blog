package cn.shu.blog.controller;

import cn.shu.blog.AddArticles;
import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.Comment;
import cn.shu.blog.beans.SearchArticle;
import cn.shu.blog.service.ArticleServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 舒新胜
 * @项目 MyBlog B
 * @创建时间 2020-5-7 19:10
 */
/*
* 处理器
* */
@Controller
@RequestMapping("/article")
public class BlogArticleHandler {
   @Autowired
   private ArticleServiceInter articleServiceInter=null;
   @RequestMapping("/hot.action")
   public String getHotArticle(HttpServletRequest request){
        List<Article> hotArticles = articleServiceInter.getHotArticles();
        if (hotArticles==null){
            hotArticles=new ArrayList<>();
        }
        /* Log.logChange("获取热门文章:"+hotArticles.size());*/
        request.setAttribute("articles",hotArticles);
        return "forward:/WEB-INF/jsp/hotArticle.jsp";

   }
   @RequestMapping("/detail/{articleId}.action")
   public String showArticleDetail(HttpServletRequest request, @PathVariable String articleId){
       ServletContext servletContext = request.getServletContext();
       //获取每页显示的页数
       String pageCommNumStr=servletContext.getInitParameter("pageCommNum");
       int pageCommNum = 5;
       if ( pageCommNumStr!= null) {
           pageCommNum = Integer.parseInt(pageCommNumStr.trim());
       }
       //获取文章信息
       //增加访问次数
       articleServiceInter. addVisitRecord(articleId);
       Article article=articleServiceInter.getSingleArticle(articleId);

       if (article!=null) {

           request.setAttribute("recommendArticles",new ArrayList<>());
           request.setAttribute("pageCommNum",pageCommNum);
           request.setAttribute("articleInfo", article);
           //swf文件
           if (".swf".equalsIgnoreCase(article.getFileType())){
               //转换为swf方便显示 转换失败
               if(!AddArticles.turnSwfJar(article.getSourceFilePath(), article.getTargetFilePath())){
                   return "forward:/WEB-INF/jsp/404.jsp";
               }
               return "forward:/WEB-INF/jsp/article.jsp";
               //html文件
           }else if (".html".equalsIgnoreCase(article.getFileType()) || ".htm".equalsIgnoreCase(article.getFileType())){
               request.setAttribute("html", article.getTargetFilePath());
               return "forward:/WEB-INF/jsp/articleHtml.jsp";
           }

       }

       return "forward:/WEB-INF/jsp/404.jsp";
   }
   @RequestMapping("/recommend.action")
   public String getRecommendArticle(HttpServletRequest request, String articleId,String title){
           //获取推荐文章
           List<Article> recommendArticles=articleServiceInter.getRecommendArticles(articleId,title);
           request.setAttribute("recommendArticles",recommendArticles);
           return "forward:/WEB-INF/jsp/recommendArticle.jsp";
   }
   @RequestMapping("/comment.action")
   public String getComment(HttpServletRequest request, String currPage){
       //获取每页加载的页数
       ServletContext servletContext = request.getServletContext();
       int pageCommNum=5;
       if (servletContext.getInitParameter("pageCommNum")!=null){
           pageCommNum=Integer.parseInt(servletContext.getInitParameter("pageCommNum").trim());
       }


        String articleId=request.getParameter("articleId");
       System.out.println(articleId);
       //获取文章的评论
       List<Comment> articleComments= articleServiceInter.getArticleComments(articleId,currPage,pageCommNum);
       if(articleComments!=null && articleComments.size()>0) {
           request.setAttribute("pageCommNum",pageCommNum);
           request.setAttribute("articleComments",articleComments);
       }
       return "forward:/WEB-INF/jsp/comments.jsp";

   }
   @ResponseBody
   @RequestMapping(value = "/addComment.action")
   public String addComment(HttpServletRequest request, Comment comment){
/*       //评论文章ID判断
       String articleId=request.getParameter("articleId");
       //评论内容判断
       String comment=request.getParameter("commentContent");*/
       //评论用户判断
       Object user=request.getSession().getAttribute("user");
       String s = articleServiceInter.articlesComment(comment, user);
       return s;
   }
   @ResponseBody
   @RequestMapping(value = "/search.action",produces = "application/json")
   public SearchArticle searchArticle(String searchStr, String currPage, String pageNum){
       System.out.println(searchStr);
       System.out.println(currPage);
       System.out.println(pageNum);
    return articleServiceInter.searchArticle(searchStr,Integer.parseInt(currPage),Integer.parseInt(pageNum));
   }
    @ResponseBody
    @RequestMapping(value = "/searchIas.action")
    public String searchArticleIAS(String searchStr, String currPage, String pageNum){
        System.out.println(searchStr);
        System.out.println(currPage);
        System.out.println(pageNum);
        List<Article> articles = articleServiceInter.searchArticle(searchStr, Integer.parseInt(currPage), Integer.parseInt(pageNum)).getArticles();
        StringBuilder stringBuilder=new StringBuilder();
        for (Article article : articles) {
            stringBuilder.append("<article class='excerpt excerpt-1'>" + "<a class='focus' href='article.jsp' title=''> " + "<img class='thumb' data-original='/images/logo.png' src=/").append(article.getImagePath()).append(" /> ").append("</a>").append("<header>").append("<a class='cat' href='program'>").append(article.getCategoryName()).append("<i></i></a>").append("<h2><a href='article.jsp' title=''>").append(article.getTitle()).append("</a></h2>").append("</header>").append("<p class='meta'>").append("<time class='time'><i class='glyphicon glyphicon-time'></i>").append(article.getUpdateDate()).append("</time>").append("<span class='views'><i class='glyphicon glyphicon-eye-open'></i> ").append(article.getVisitors()).append(" 人围观</span>").append("<a class='comment' href='article.jsp#comment'><i class='glyphicon glyphicon-comment'></i> ").append(article.getCommNum()).append(" 评论</a>").append("</p>").append("<p class='note'>").append(article.getDescription()).append("</p>").append("</article>");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
