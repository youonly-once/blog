package cn.shu.blog.utils.lucene;

import cn.shu.blog.beans.Article;
import cn.shu.blog.dao.ArticleMapper;
import cn.shu.blog.service.ArticleService;
import cn.shu.blog.utils.database.JDBCUtil;
import cn.shu.blog.utils.database.ResultSet2Bean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @作者 舒新胜
 * @项目 blog
 * @创建时间 2020/6/7 14:05
 */
public class LuceneUtilForArticle {
    public static void createIndex(String rootPath,List<Article> articleList) throws IOException {

        if (articleList != null && articleList.size()>0){
            LuceneIndexUntil.createIndex(articleList,rootPath+"\\article_index");
        }
    }
    private static List<Article> getArticles(){
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            conn=JDBCUtil.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT xx.id,title,createDate,updateDate,userId,visitors,fileType,targetFilePath,sourceFilePath,description,imagePath,commNum,categoryName" +
                    "                 FROM (SELECT *,CASE WHEN  num IS NULL THEN 0 ELSE num END AS commNum" +
                    "                 FROM article LEFT JOIN (SELECT COUNT(*)AS num,articleId FROM comment GROUP BY articleId) AS comments_count" +
                    "                 ON comments_count. articleId= article.id" +
                    "                 ORDER BY updatedate DESC ) AS xx,category WHERE xx.categoryId=category.id;");
            return ResultSet2Bean.getList(rs,Article.class);
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,st,rs);
        }
        return null;
    }
}
