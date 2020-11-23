package cn.shu.blog.service;

import cn.shu.blog.beans.Category;
import cn.shu.blog.dao.CategoryMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

    @Resource
    private CategoryMapper categoryMapper = null;

    @Override
    public List<Category> selectByAll(HashMap<String,Object> params) {
        return categoryMapper.selectByAll(params);
    }

    public int deleteByPrimaryKey(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }

    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

    public int insertOrUpdate(Category record) {
        return categoryMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Category record) {
        return categoryMapper.insertOrUpdateSelective(record);
    }

    public int updateBatch(List<Category> list) {
        return categoryMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Category> list) {
        return categoryMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Category> list) {
        return categoryMapper.batchInsert(list);
    }
}



