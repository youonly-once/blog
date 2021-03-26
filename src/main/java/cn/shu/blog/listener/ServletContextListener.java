package cn.shu.blog.listener;

import cn.shu.blog.ArticleUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * ServletContext是web全局 web启动时启动
 * 可以监听其创建，然后将当前web应用路径保存，供JSP使用
 * @author SXS
 */
@Component
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Resource
    private ArticleUtil articleUtil = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String webPath= context.getContextPath();
         context.setAttribute("webPath",webPath);
         //扫描文档
        ThreadPoolExecutor threadPoolExecutor
                = new ThreadPoolExecutor(
                        0
                , 1
                , 0
                , TimeUnit.MILLISECONDS
                , new SynchronousQueue<>()
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.DiscardPolicy());
        threadPoolExecutor.execute(() -> articleUtil.scanArticle());
        threadPoolExecutor.shutdown();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
