package cn.shu.blog.service;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/4/29
 */

import cn.shu.blog.beans.User;
import cn.shu.blog.exception.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 静态代理模式
 * 代理者
 */
//@Service("userServiceInter") //Spring管理
public class UserServiceStaticProxy implements UserServiceInter {
    /**
     * 被代理者
     */
    //注入被代理者
    @Autowired
    @Qualifier("userService")
    UserServiceInter userServiceInter=null;
    @Override
    public User findUserByUsername(String userName) throws UserException {
        return userServiceInter.findUserByUsername(userName);
    }

    @Override
    public User findUserByNickname(String nickName) throws UserException {
        return userServiceInter.findUserByNickname(nickName);
    }

    @Override
    public User findUserByEmail(String mail) throws UserException {
       return userServiceInter.findUserByEmail(mail);
    }

    @Override
    public String registerUser(User newUser, String captcha, String serverCaptcha) {
        return userServiceInter.registerUser(newUser,captcha,serverCaptcha);
    }

    @Override
    public User isUserExists(User loginUser) {
        /*
         *代理者中做一些日志记录
         */
        System.out.println("用户登录:静态代理");
        return userServiceInter.isUserExists(loginUser);
    }



    @Override
    public boolean checkCaptcha(String captchaClient, Object captchaServ) {
        return userServiceInter.checkCaptcha(captchaClient,captchaServ);
    }
}
