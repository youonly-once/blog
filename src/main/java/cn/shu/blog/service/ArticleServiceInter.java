package cn.shu.blog.service;

import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.Comment;
import cn.shu.blog.beans.SearchArticle;

import java.sql.SQLException;
import java.util.List;

public interface ArticleServiceInter {

    public  void addVisitRecord(String articleId);
    public Article getSingleArticle(String articleId);
    public List<Article> getHotArticles();
    public  List<Article> getArticles(int currPage,  String categoryId);
    public List<Article> getRecommendArticles(String currArticleId,String title);
    public int getArticlesCount(String categoryId) throws SQLException;
    public List<Comment> getArticleComments(String articleId, String currPage, int pageCount);
    public String articlesComment(Comment comment, Object user);

    SearchArticle searchArticle(String searchStr, Integer currPage, Integer pageNum);
}
