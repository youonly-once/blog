package cn.shu.blog.dao;

import cn.shu.blog.beans.User;

import java.sql.SQLException;

public interface UserDaoInter {
    public User findUserByUsername(String username) throws SQLException;
    public int registerUser(User newUser);
    public User userLogin(User loginUser);
    public User findUserByNickname(String nickName) throws SQLException;
    public User findUserByEmail(String mail) throws SQLException;

}
