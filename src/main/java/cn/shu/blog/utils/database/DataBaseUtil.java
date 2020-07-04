package cn.shu.blog.utils.database;


import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class DataBaseUtil {

    /**
     * 数据库查询操作
     */
    public static List<Map<String, String>> databaseQuery(String sql, Connection con) {
        // 若报错没有返回结果集，需要再存储过程begin后添加:SET NOCOUNT ON
       PreparedStatement statement = null;
        ResultSet result = null;
        try {

            statement = con.prepareStatement(sql);
            if (statement == null) {
                log.error("error：statement is null");
                return null;
            }
            result = statement.executeQuery();

            return ResultSet2Map(result);
        } catch ( SQLException | NullPointerException e) {

            e.printStackTrace();
            log.error("error：" + e.getMessage());
            return null;
        } finally {
            JDBCUtil.close(con,statement,result);
        }
    }
    /*
     * 数据库更新操作
     */
    public static boolean databaseUpdate(String sql,Connection con) {
        // 若报错没有返回结果集，需要再存储过程begin后添加:SET NOCOUNT ON

        Statement statement = null;
        int result = 0;

        try {

            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (statement == null) {
                log.error("error：statement is null");
                return false;
            }
            result = statement.executeUpdate(sql);
            if (result < 1) {
                log.error("error：result < 1");

                return false;
            }else{
                log.info("更新成功："+result);
            }
            return true;

        } catch ( SQLException | NullPointerException e) {

            e.printStackTrace();
            log.error("error：" + e.getMessage());
            return false;
        } finally {

            JDBCUtil.close(con,statement,null);
        }
    }
    public static List<Map<String, String>> ResultSet2Map(ResultSet resultSet) throws SQLException {

        Map<String, String> map;
        List<Map<String, String>> list = new ArrayList<>();
        while (resultSet.next()) {
            map = new HashMap<>();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                map.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
            }
            list.add(map);
        }
        return list;
    }
}
