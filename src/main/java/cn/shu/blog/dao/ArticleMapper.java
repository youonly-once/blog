package cn.shu.blog.dao;

import cn.shu.blog.beans.Article;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    public  int addVisitRecord(int articleId);

    public  Article getSingleArticle(int articleId);

    public  List<Article> getHotArticles();

    public  int getArticlesCount(@Param("categoryId") int categoryId);

    public  List<Article> getArticles(@Param("currPage") int currPage, @Param("pageNum") int pageNum, @Param("categoryId") Integer categoryId);

    public  List<Article> getExceptCurrArticles(int currArticleId);

    public int addArticlesComment(@Param("comment") String comment, @Param("articleId") int articleId, @Param("userId") int userId, @Param("dateTime") String dateTime);

    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Article record);

    int insertOrUpdate(Article record);

    int insertOrUpdateSelective(Article record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Article record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Article record);

    int updateBatch(List<Article> list);

    int updateBatchSelective(List<Article> list);

    int batchInsert(@Param("list") List<Article> list);

    List<Article> selectByAll(Article article);

    List<Article> getArticlesForIndex();

}