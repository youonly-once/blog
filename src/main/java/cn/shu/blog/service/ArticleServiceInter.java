package cn.shu.blog.service;

import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.SearchArticle;

import java.sql.SQLException;
import java.util.List;

public interface ArticleServiceInter {

    public void addVisitRecord(String articleId);

    public Article getSingleArticle(String articleId);

    public List<Article> getHotArticles();

    public List<Article> getArticles(int currPage, String categoryId);

    public List<Article> getRecommendArticles(String currArticleId, String title);

    public int getArticlesCount(String categoryId) throws SQLException;

    SearchArticle searchArticle(String searchStr, Integer currPage, Integer pageNum);

    public int deleteByPrimaryKey(Integer id);

    public int insert(Article record);

    public int insertOrUpdate(Article record);

    public int insertOrUpdateSelective(Article record);

    public int insertSelective(Article record);

    public Article selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Article record);

    public int updateByPrimaryKey(Article record);

    public int updateBatch(List<Article> list);

    public int updateBatchSelective(List<Article> list);

    public int batchInsert(List<Article> list);
}
