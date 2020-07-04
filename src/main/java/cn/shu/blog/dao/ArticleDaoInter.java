package cn.shu.blog.dao;

import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.Comment;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDaoInter {
    public  int addVisitRecord(int articleId);
    public  Article getSingleArticle(int articleId);
    public  List<Article> getHotArticles();
    public  int getArticlesCount(@Param("categoryId") int categoryId)throws SQLException;
    public  List<Article> getArticles(@Param("currPage") int currPage, @Param("pageNum") int pageNum, @Param("categoryId") int categoryId);
    public  List<Article> getExceptCurrArticles(int currArticleId);
    public List<Comment> getArticleComments(@Param("articleId") int articleId, @Param("currPage") int currPage, @Param("pageCount") int pageCount);
    public int addArticlesComment(@Param("comment") String comment, @Param("articleId") int articleId, @Param("userId") int userId, @Param("dateTime") String dateTime);
}
