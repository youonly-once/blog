package cn.shu.blog.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听在线人数
 */
@Slf4j
@Component
@WebListener
public class OnlineNumberListener implements HttpSessionListener {
    private int onlineNumber=0;

    public OnlineNumberListener() {
        // log.info("Listener 构造");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // log.info("session被创建");
        onlineNumber=onlineNumber+1;
        httpSessionEvent.getSession().setAttribute("onlineNumber",onlineNumber);
        log.info("在线人数:" + onlineNumber);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
       //  log.info("session被销毁");
        onlineNumber=onlineNumber-1;
        httpSessionEvent.getSession().setAttribute("onlineNumber",onlineNumber);
        log.info("在线人数:" + onlineNumber);
    }
}
