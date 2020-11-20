package cn.shu.blog.beans;

import lombok.Data;

/**
 * @author SXS
 */
@Data
public class Category {
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;
}