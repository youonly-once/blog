package cn.shu.blog.service;

import cn.shu.blog.beans.User;
import cn.shu.blog.exception.UserException;

public interface UserServiceInter {
    public User findUserByUsername(String userName) throws UserException;
    public User findUserByNickname(String nickName) throws UserException;
    public User findUserByEmail(String mail) throws UserException;
    public String registerUser(User newUser, String captcha, String serverCaptcha);
    public User isUserExists(User loginUser);
    public boolean checkCaptcha(String captchaClient, Object captchaServ);
}
