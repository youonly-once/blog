package cn.shu.blog.interceptor;

import cn.shu.blog.beans.User;
import cn.shu.blog.service.UserServiceInter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-12 12:38
 */
public class AutoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
        UserServiceInter userServiceInter = (UserServiceInter) context.getBean("userServiceInter");
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies==null){
            return true;
        }
        User user=new User();
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())){
                user.setUsername(cookie.getValue());
                if (user.getPassword()!=null){
                    break;
                }
            }else if ("password".equals(cookie.getName())) {
                user.setPassword(cookie.getValue());
                if (user.getUsername()!=null){
                    break;
                }
            }
        }
        User userExists = userServiceInter.isUserExists(user);
        if (userExists!=null){
            httpServletRequest.getSession().setAttribute("user",userExists);
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
