package cn.shu.blog.utils.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 18:12
 * 用于管理事务
 */
public class TransactionManager {
    //线程本地变量
    //为解决线程同步问题 相当于map实现 每一个线程get的时候会获取该线程再map中对应的Connection
    //如果没有对应的，则调用初始化方法，返回一个Connection，并保存到map中，下次该线程获取的时候就是这个Connection,保证不会发生多线程安全问题
    //这里匿名内部类重写了初始化方法
    private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>(){

        @Override
        protected Connection initialValue() {
            try {
                return JDBCUtil.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    };
    public static Connection getConnection(){
        return tl.get();
    }

    /**
     * 开启事务
     */
    public static void startTrans(){
        try {
            tl.get().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 提交事务
     */
    public static void commitTrans(){
        try {
            tl.get().commit();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTrans(){
        try {
            tl.get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 释放资源
     */
    public static void close(){

        JDBCUtil.close(tl.get(),null,null);
        //移除当前线程对应的Connection对象
        tl.remove();
    }

}
