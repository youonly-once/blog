package cn.shu.blog.dao;

import cn.shu.blog.beans.Category;

import java.util.List;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/5/1 14:44
 */

public interface CategoryDaoInter {
    List<Category> getCategories();
}
