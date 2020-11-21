package cn.shu.blog.dao;

import cn.shu.blog.beans.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Comment record);

    int insertOrUpdate(Comment record);

    int insertOrUpdateSelective(Comment record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Comment record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Comment record);

    int updateBatch(List<Comment> list);

    int updateBatchSelective(List<Comment> list);

    int batchInsert(@Param("list") List<Comment> list);

    List<Comment> selectByAll(Comment comment);
}