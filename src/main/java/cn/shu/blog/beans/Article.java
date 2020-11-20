package cn.shu.blog.beans;

import lombok.Data;

import java.text.SimpleDateFormat;import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class Article {
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 访客数
     */
    private Integer visitors;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 目标文件路径
     */
    private String targetFilePath;

    /**
     * 源文件路径
     */
    private String sourceFilePath;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 评论数量
     */
    private Long commNum;
    /**
     * 文章分类名称
     */
    private String categoryName;

    /**
     * 用户昵称
     */
    private String nickname;
    private String format(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    private List<Comment> commentList;

}