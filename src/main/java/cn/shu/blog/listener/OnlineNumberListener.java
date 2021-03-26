package cn.shu.blog.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听网页在线人数
 * @author SXS
 */
@Slf4j
@Component
@WebListener
public class OnlineNumberListener implements HttpSessionListener {
    /**
     * 网站在线数量
     */
    private int onlineNumber=0;

    public OnlineNumberListener() {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        onlineNumber++;
        httpSessionEvent.getSession().setAttribute("onlineNumber",onlineNumber);
        log.info("在线人数:" + onlineNumber);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {


        httpSessionEvent.getSession().setAttribute("onlineNumber",onlineNumber);
        log.info("在线人数:" + onlineNumber);
    }
}
