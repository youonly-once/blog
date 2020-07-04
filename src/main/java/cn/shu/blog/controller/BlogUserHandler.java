package cn.shu.blog.controller;

import cn.shu.blog.beans.User;
import cn.shu.blog.service.UserServiceInter;

import cn.shu.blog.utils.MD5;
import cn.shu.blog.utils.captcha.VerifyCode;
import cn.shu.blog.utils.cookies.CookiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-7 19:10
 */
/*
* Spring MVC处理器  User相关
* */
@Slf4j
@Controller
@RequestMapping("/user")
public class BlogUserHandler {
    @Autowired
    UserServiceInter userServiceInter;

    @ResponseBody
    @RequestMapping(value = "/login.action",produces = "text/html;charset=utf-8")
    public String login(HttpServletRequest request, HttpServletResponse response, User loginUser, String remName, String autoLogin) throws IOException {
        loginUser.setPassword(MD5.md5(loginUser.getPassword()));
        //判断用户是否存在
        User user = userServiceInter.isUserExists(loginUser);
        if (user!=null){
            //自动登录
            if("true".equals(autoLogin)){
                response.addCookie(CookiesUtil.setCookie("username",loginUser.getUserName(),request.getContextPath()+"/"));
                response.addCookie(CookiesUtil.setCookie("password", loginUser.getPassword(),request.getContextPath()+"/"));
            }else{//没有勾选//删除之前的
                //记住用户名 设置cookie
                if("true".equals(remName)){
                    response.addCookie(CookiesUtil.setCookie("username",loginUser.getUserName(),request.getContextPath()+"/"));
                }else{//没有勾选//删除之前的
                    response.addCookie(CookiesUtil.delCookie("username",loginUser.getUserName(),request.getContextPath()+"/"));
                }
                response.addCookie(CookiesUtil.delCookie("password",loginUser.getPassword(),request.getContextPath()+"/"));
            }
            //保存用户信息到session中
            request.getSession().setAttribute("user",user);
            return "success";
        }else{

            return "用户名密码错误";
        }
    }
    @RequestMapping("/logout.action")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //Log.logChange("用户注销:"+request.getSession().getAttribute("user"));
        User user= (User) session.getAttribute("user");
        //删除自动登录cookie
        response.addCookie(CookiesUtil.delCookie("password",user.getPassword(),request.getContextPath()+"/"));
        //杀死session
        session.invalidate();
        //跳转回首页，重定向
        return "redirect:/";

    }
    @RequestMapping(value = "/register.action")
    public void registerUser(@Valid User newUser, Errors errors, String captcha, @SessionAttribute("captcha") String serCaptcha, HttpServletResponse response) throws  IOException {
        if (errors.hasErrors()){
            for(FieldError fieldError : errors.getFieldErrors()) {
                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                response.getWriter().write(fieldError.getDefaultMessage()+",5秒后返回注册页面");
                response.setHeader("refresh", "5;url=/register/register.html");
                return;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        //添加用户
        newUser.setPassword(MD5.md5(newUser.getPassword()));
        String msg=userServiceInter.registerUser(newUser,captcha,serCaptcha);
        if ("success".equals(msg)){
            log.info("用户注册成功:"+newUser);
            response.getWriter().write("注册成功,3秒后跳转到首页");
            response.setHeader("refresh", "3;url=/");
        }else{
            log.error("用户注册失败:"+msg);
            response.getWriter().write(msg+",5秒后返回注册页面");
            response.setHeader("refresh", "5;url=/register/register.html");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/registerCheck.action",params = "method",produces = "text/html;charset=utf-8")
    public String registerAjaxCheck(String method, String userName, String nickName, String mail, String captcha, @SessionAttribute(value = "captcha",required = false) String serCaptcha){
        System.out.println(method);
        String msg="success";
        try {             //校验用户名
            if ("userName".equals(method)) {
                User user = userServiceInter.findUserByUsername(userName);
                if (user != null) {
                    msg= "用户名已被注册";
                }
                //校验昵称
            } else if ("nickName".equals(method)) {
                User user = userServiceInter.findUserByNickname(nickName);
                if (user != null) {
                    msg = "昵称已被使用";
                }
            } else if ("mail".equals(method)) {
                User user = userServiceInter.findUserByEmail(mail);
                if (user != null) {
                    msg = "邮箱已被注册";
                }
            }else if ("captcha".equals(method)) {
                boolean b = userServiceInter.checkCaptcha(captcha,serCaptcha);
                if (!b) {
                    msg = "验证码错误";
                }
            }
           return msg;
        }catch(Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }
    @RequestMapping("/Captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        //CreateCaptcha drawYzm=new CreateCaptcha();

        // String code= drawYzm.getCode(response.getOutputStream());
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.drawImage(response.getOutputStream());
        String code=verifyCode.getCode();
        //6.保存到session中
        session.setAttribute("captcha",code);
        //7.禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");

    }

}
