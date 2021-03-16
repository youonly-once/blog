package cn.shu.blog.listener;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 监听HttpSession属性的添加与修改
 */
/*@Component
@WebListener*/
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent){
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent){
    }
}
