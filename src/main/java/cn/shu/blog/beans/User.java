package cn.shu.blog.beans;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 封装user对象
 *
 */

@Component("user")//交由Spring管理Bean 默认id user，指定id user
@Scope(value = "prototype")//由Spring创建多例
public class User {
    //初始值为访客
    @Value("${userId}")
    private int id;

    //初始值为访客
    @Value("${account}")
    @NotNull(message = "用户名为null")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    private Date createDate;

    @NotNull(message = "密码不能为null")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotNull(message = "昵称不能为null")
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @NotNull(message = "邮箱不能为null")
    @NotEmpty(message = "邮箱不能为空")
    @Email(message="邮箱不正确")
    private String email;

    private String permission;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", createDate=" + createDate +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
