package cn.shu.blog.beans;

import java.util.Date;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-9 12:50
 */
public class Comment {
    private int id;
    private Date createDate;
    private String location;
    private String comment;
    private User user;
    private Article article;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }



    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", location='" + location + '\'' +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", article=" + article +
                '}';
    }
}
