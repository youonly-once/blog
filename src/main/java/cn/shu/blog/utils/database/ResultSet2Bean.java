package cn.shu.blog.utils.database;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSet2Bean <T>{
    /**
     * 通过反射将 ResultSet转Bean类 无需手动设置setString
     * @param rs ResultSet
     * @param clazz bean类的class对象
     * @param <T> 泛型，Bean类的类型
     * @return bean对象list
     */
    public static <T> List<T> getList(ResultSet rs, Class<T> clazz) {
        // 获取JavaBean里面的所有属性
        Field[] field = clazz.getDeclaredFields();
        T obj = null;
        // 创建list容器，存放映射后的JavaBean对象
        List<T> list = new ArrayList<T>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取记录集中的列数
            int counts = rsmd.getColumnCount();
            // 定义counts个String 变量
            String[] columnNames = new String[counts];
            // 给每个变量赋值(字段名称全部转换成大写)
            for (int i = 0; i < counts; i++) {
                // getColumnLabel() 取别名(如有) 即SQL AS的值
                // getColumnName() 取字段名：
                columnNames[i] = rsmd.getColumnLabel(i + 1).toUpperCase();
            }
            List columnNameList = Arrays.asList(columnNames);
            //  log.info(columnNameList);
            // 开始遍历查询结果
            while (rs.next()) {
                // 创建Javabean对象
                obj = clazz.newInstance();
                // 循环将查询结果写入对应的JavaBean属性中
                for (Field f : field) {
                    // 获取该字段名称
                    String name = f.getName();
                    // 判断该字段是否在ResultSet的字段里，在的话才去进行赋值操作
                    // 如果不进行判断的话，在JavaBean字段比ResultSet字段多的情况下，会抛异常
                    if(columnNameList.contains(name.toUpperCase())) {
                        // 判断是否查询到对应的值
                        if (rs.getObject(name) != null) {
                            // 跳过检查，这里访问的时私有属性
                            f.setAccessible(true);
                            // 将查询到的值付给对应的属性
                            f.set(obj, rs.getObject(name));
                        }
                    }
                }
                // 把结果的每一列都添加到list中
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            System.err.println("结果集遍历失败");
            e.printStackTrace();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("数据读取失败");
            return null;
        }

    }

}
