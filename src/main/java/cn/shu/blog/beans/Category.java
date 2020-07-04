package cn.shu.blog.beans;

import org.springframework.stereotype.Component;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/5/1 14:38
 */
@Component
public class Category {
    private int id;
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
