package cn.shu.blog.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

@Component
@WebListener
public class ServletRequestListener implements javax.servlet.ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        // log.info("ServletRequest销毁");

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
       //  log.info("ServletRequest创建");
    }
}
