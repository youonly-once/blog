package cn.shu.blog.service;

import cn.shu.blog.beans.Category;
import cn.shu.blog.dao.CategoryDaoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/5/1 14:40
 */
@Service("categoryServiceInter")
@Lazy
@Scope(scopeName = "singleton")
public class CategoryService implements CategoryServiceInter {

    @Autowired
    private CategoryDaoInter categoryDaoInter=null;
    public List<Category> getCategories(){
       return categoryDaoInter.getCategories();
    }
}
