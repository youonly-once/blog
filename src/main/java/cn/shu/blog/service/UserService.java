package cn.shu.blog.service;

import cn.shu.blog.beans.User;
import cn.shu.blog.dao.UserMapper;
import cn.shu.blog.exception.UserException;
import cn.shu.blog.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;import java.util.List;

@Service("userServiceInter")
@Lazy
@Scope(value = "singleton")
@Slf4j
public class UserService implements UserServiceInter {
    @Autowired
    /**
     * 自动注入，首先寻找 ArticleDaoInter类型的(包括实现了的类)，找到唯一注入，
    没找到或找到多个则按id(articleDaoInter)查找,查找到则注入*/
    private UserMapper userMapper;

    // private UserService() {}

    @PostConstruct
    public void init() {
        /*this.userDaoInter = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");*/
    }

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户不存在返回null
     */
    @Override
    public User findUserByUsername(String userName) throws UserException {
        if (StringUtil.isEmpty(userName)) {
            throw new UserException("用户名不能为空");
        }

        HashMap<String, Object> params = new HashMap<>(1);
        params.put("account", userName);
        return userMapper.selectByAll(params).get(0);
    }

    /**
     * 判断昵称是否存在
     *
     * @param nickName 昵称
     * @return 用户
     */
    @Override
    public User findUserByNickname(String nickName) throws UserException {
        if (StringUtil.isEmpty(nickName)) {
            throw new UserException("昵称不能为空");
        }

        HashMap<String, Object> params = new HashMap<>(1);
        params.put("nickName", nickName);
        return userMapper.selectByAll(params).get(0);
    }

    /**
     * 判断邮箱是否存在
     *
     * @param email 邮箱地址
     * @return 用户
     */
    @Override
    public User findUserByEmail(String email) throws UserException {
        if (StringUtil.isEmpty(email)) {
            throw new UserException("邮箱不能为空");
        }

        HashMap<String, Object> params = new HashMap<>(1);
        params.put("email", email);
        return userMapper.selectByAll(params).get(0);
    }


    /**
     * 添加用户
     *
     * @param newUser 新用户
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String registerUser(User newUser, String captcha, String serverCaptcha) {
        String s = dataCheck(newUser, captcha, serverCaptcha);
        if (!"success".equals(s)) {
            return s;
        }
        try {
            User u = findUserByUsername(newUser.getAccount());
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
            if (userMapper.insert(newUser) > 0) {
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
     */
    private String dataCheck(User user, String captcha, String serCaptcha) {

/*        if (StringUtil.isEmpty(user.getAccount())) {
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
        }*/
        if (StringUtil.isEmpty(captcha)) {
            return "验证码不能为空";
        }

        /*
         * ^匹配字符开头
         *  \\w匹配数字 字母 下划线  +至少出现一次
         *  \\. 配置字符 .
         *  $匹配字符结尾
         */

/*        if (!user.getEmail().matches("^\\w+@\\w+(\\.\\w+)+$")) {
            return "邮箱格式不正确";
        }*/
        /*
         * 验证码判断
         */
        if (!checkCaptcha(captcha, serCaptcha)) {
            return "验证码错误";
        }
        return "success";
    }

    /**
     * 登录判断用户是否存在
     */
    @Override
    public User isUserExists(User loginUser) {
        if (StringUtil.isEmpty(loginUser.getAccount())) {
            return null;
        }
        if (StringUtil.isEmpty(loginUser.getPassword())) {
            return null;
        }
        return userMapper.selectByAllWithObject(loginUser).get(0);
    }

    /**
     * 判断验证码是否正确
     *
     * @param captchaClient 客户端验证码
     * @param captchaServe 服务端验证码
     * @return 校验值
     */
    @Override
    public boolean checkCaptcha(String captchaClient, Object captchaServe) {
        if (StringUtil.isEmpty(captchaClient)) {
            return false;
        }
        if (captchaServe == null) {
            return false;
        }
        if (StringUtil.isEmpty((String) captchaServe)) {
            return false;
        }
        if (!captchaClient.equalsIgnoreCase(captchaServe.toString())) {
            return false;
        }
        return captchaClient.trim().length() == 4;
    }

    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) {
        return userMapper.insert(record);
    }

    public int insertOrUpdate(User record) {
        return userMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(User record) {
        return userMapper.insertOrUpdateSelective(record);
    }

    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<User> list) {
        return userMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<User> list) {
        return userMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }
}

