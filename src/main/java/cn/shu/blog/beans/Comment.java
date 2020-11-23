package cn.shu.blog.beans;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class Comment {
    private Integer id;

    /**
    * 文章id
    */
    private Integer articleId;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 创建时间
    */
    private Date createDate;

    /**
    * 用户位置
    */
    private String location;

    /**
    * 评论内容
    */
    private String comment;
    /**
     * 用户
     */
    private User user;

}