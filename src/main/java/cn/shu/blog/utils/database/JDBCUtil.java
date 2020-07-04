package cn.shu.blog.utils.database;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    private static DruidDataSource druidDataSource=new DruidDataSource();
    static {
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/myblog?allowMultiQueries=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    }
    public static Connection getConnection() throws SQLException {

            return druidDataSource.getConnection();

    }
    public static void close(Connection conn, Statement stat, ResultSet rs){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                conn=null;
            }
        }
        if (stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                stat=null;
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                rs=null;
            }
        }
    }
}
