package cn.shu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-11 15:43
 */
@Controller
public class BlogJSPForward {
    /**
     * 不能直接访问jsp，通过调用接口返回jsp
     * 比如/header/hello.action
     * 返回hello
     * 匹配后缀.jsp，前缀/web-inf/
     * 最终/web-inf/hello.jsp
     * @param tags
     * @return
     */
    @RequestMapping("/header/{tags}.action")
    public String tags(@PathVariable String tags){
        return "/"+tags;
    }

    @RequestMapping("/{tags}.action")
    public String search(@PathVariable String tags){
        return "/"+tags;
    }
}
