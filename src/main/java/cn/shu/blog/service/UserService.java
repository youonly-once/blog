package cn.shu.blog.service;

import cn.shu.blog.beans.User;
import cn.shu.blog.dao.UserDaoInter;
import cn.shu.blog.exception.UserException;
import cn.shu.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Service("userServiceInter")
@Lazy
@Scope(value = "singleton")
public class UserService implements UserServiceInter {
    @Autowired
    //自动注入，首先寻找 ArticleDaoInter类型的(包括实现了的类)，找到唯一注入，
    //没找到或找到多个则按id(articleDaoInter)查找,查找到则注入
    private UserDaoInter userDaoInter;

   // private UserService() {}

    @PostConstruct
    public void init(){
        /*this.userDaoInter = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");*/
    }
    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户不存在返回null
     */
    public User findUserByUsername(String userName) throws UserException {
        if (StringUtil.isEmpty(userName)){
            throw new UserException("用户名不能为空");
        }
        try {
            return userDaoInter.findUserByUsername(userName);
        } catch (SQLException e) {
            throw new UserException("系统错误");
        }
    }

    /**
     * 判断昵称是否存在
     * @param nickName
     * @return
     */
    public User findUserByNickname(String nickName) throws UserException {
        if (StringUtil.isEmpty(nickName)){
            throw new UserException("昵称不能为空");
        }
        try {
            return userDaoInter.findUserByNickname(nickName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("系统错误");
        }
    }
    /**
     * 判断邮箱是否存在
     * @param mail
     * @return
     */
    public User findUserByEmail(String mail) throws UserException {
        if (StringUtil.isEmpty(mail)){
            throw new UserException("邮箱不能为空");
        }
        try {
            return userDaoInter.findUserByEmail(mail);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("系统错误");
        }
    }
    /**
     * 添加用户
     * @param newUser
     */
    //需要事务管理

    @Transactional(rollbackFor = Throwable.class)//开启事务管理
    public String registerUser(User newUser, String captcha, String serverCaptcha) {
        String s = dataCheck(newUser, captcha, serverCaptcha);
        if (!"success".equals(s)){
            return s;
        }
        try {
            User u = findUserByUsername(newUser.getUserName());
            if (u != null) {
                return "用户名已被注册";
            }
            u = findUserByNickname(newUser.getNickname());
            if (u != null) {
                return "昵称已存在";
            }
            u = findUserByEmail(newUser.getEmail());
            if (u != null) {
                return "邮箱已注册";
            }
            if (userDaoInter.registerUser(newUser)>0){
                //int i = 1 / 0;
                return "success";
            }
        } catch (UserException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "注册失败,请稍后再试";
    }
    /**
     * 校验注册数据是否正常
     *
     */
    private String dataCheck(User user, String captcha, String serCaptcha)  {

        if (StringUtil.isEmpty(user.getUserName())) {
            return "用户名不能为空";
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            return "密码不能为空";
        }
        if (StringUtil.isEmpty(user.getNickname())) {
            return "昵称不能为空";
        }
        if (StringUtil.isEmpty(user.getEmail())) {
            return "email不能为空";
        }
        if (StringUtil.isEmpty(captcha)) {
            return "验证码不能为空";
        }

        /**
         * ^匹配字符开头
         *  \\w匹配数字 字母 下划线  +至少出现一次
         *  \\. 配置字符 .
         *  $匹配字符结尾
         */

        if (!user.getEmail().matches("^\\w+@\\w+(\\.\\w+)+$")) {
            return "邮箱格式不正确";
        }
        /**
         * 验证码判断
         */
        if (!checkCaptcha(captcha,serCaptcha)) {
            return "验证码错误";
        }
        return "success";
    }
    /**
     * 登录判断用户是否存在
     */
    public User isUserExists(User loginUser) {
        if (StringUtil.isEmpty(loginUser.getUserName())){
            return null;
        }
        if (StringUtil.isEmpty(loginUser.getPassword())){
            return null;
        }
        return userDaoInter.userLogin(loginUser);
    }

    /**
     * 判断验证码是否正确
     * @param captchaClient
     * @param captchaServ
     * @return
     */
    public boolean checkCaptcha(String captchaClient,Object captchaServ) {
        System.out.println(captchaClient);
        System.out.println(captchaServ);
        if (StringUtil.isEmpty(captchaClient)){
            return false;
        }
        if (captchaServ==null){
            return false;
        }
        if (StringUtil.isEmpty((String) captchaServ)){
            return false;
        }
        if (!captchaClient.equalsIgnoreCase(captchaServ.toString())){
            return false;
        }
        if(captchaClient.trim().length()!=4){
            return false;
        }
        return true;
    }
}
