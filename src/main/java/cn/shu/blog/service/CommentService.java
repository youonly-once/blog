package cn.shu.blog.service;

import cn.shu.blog.beans.User;
import cn.shu.blog.dao.UserMapper;
import cn.shu.blog.utils.DateUtil;
import cn.shu.blog.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.shu.blog.dao.CommentMapper;
import cn.shu.blog.beans.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * @param articleId 文章id
     * @param currPage  马上显示第几页
     * @param pageCount 每页显示数量
     * @return 评论内容
     */

    public List<Comment> getArticleComments(String articleId, String currPage, int pageCount) {
        if (StringUtil.isEmpty(articleId)) {
            return null;
        }
        if (StringUtil.isEmpty(currPage)) {
            return null;
        }
        PageHelper.startPage(Integer.parseInt(currPage),pageCount);
        Comment comment = new Comment();


        comment.setArticleId(Integer.parseInt(articleId));
        PageInfo<Comment> pageInfo = new PageInfo<>(commentMapper.selectByAll(comment));
        List<Comment> list = pageInfo.getList();
        for (Comment c : list) {
            User user = userMapper.selectByPrimaryKey(c.getUserId());
            c.setUser(user);
        }
        return list;
    }

    /**
     * 用户评论
     *
     * @param comment 评论内容
     * @param user    评论用户
     * @return 提示信息
     */

    @Transactional(rollbackFor = Throwable.class)
    public String articleComment(Comment comment, Object user) {

        if (StringUtil.isEmpty(comment.getComment())) {
            return "不能评论空内容";
        }
        //1代表游客，默认值;
        int userId = 1;
        if (user != null) {
            userId = ((User) user).getId();
        }
        comment.setCreateDate(new Date());
        comment.setUserId(userId);
        //提交评论
        int b =commentMapper.insert(comment);
        if (b > 0) {
            return "success";
        } else {
            return "评论失败、系统错误";
        }

    }
    public int deleteByPrimaryKey(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    
    public int insertOrUpdate(Comment record) {
        return commentMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(Comment record) {
        return commentMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<Comment> list) {
        return commentMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<Comment> list) {
        return commentMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Comment> list) {
        return commentMapper.batchInsert(list);
    }
    public List<Comment> selectByAll(Comment comment){
        return commentMapper.selectByAll(comment);
    }
}
