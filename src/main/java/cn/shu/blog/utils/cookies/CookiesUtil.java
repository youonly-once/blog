package cn.shu.blog.utils.cookies;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookiesUtil {
    public static Cookie setCookie(String key, String userName, String cookiePath) throws UnsupportedEncodingException {
        //防止乱码 默认不支持中文，需要编码

         Cookie cookie = new Cookie(key, URLEncoder.encode(userName,"utf-8"));
            cookie.setMaxAge(365*24*60*60);
            cookie.setPath(cookiePath);
            return cookie;
    }
    public static Cookie delCookie(String key, String userName, String cookiePath) throws UnsupportedEncodingException {
        //防止乱码 默认不支持中文，需要编码
        Cookie cookie=new Cookie(key, URLEncoder.encode(userName,"utf-8"));
        cookie.setMaxAge(0);
        cookie.setPath(cookiePath);
        return cookie;
    }
}
