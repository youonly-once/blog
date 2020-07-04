package cn.shu.blog.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * ServletContext是web全局 web启动时启动
 * 可以监听其创建，然后将当前web应用路径保存，供JSP使用
 */
@Component
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
       // System.out.println("ServletContext创建");
        ServletContext context = servletContextEvent.getServletContext();

        String webPath= context.getContextPath();
         context.setAttribute("webPath",webPath);
           // System.out.println("source:"+servletContextEvent.getSource());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
