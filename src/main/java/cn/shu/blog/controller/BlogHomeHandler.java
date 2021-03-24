package cn.shu.blog.controller;

import cn.shu.blog.beans.Article;
import cn.shu.blog.beans.Category;
import cn.shu.blog.service.ArticleServiceInter;
import cn.shu.blog.service.CategoryServiceInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-9 9:51
 */
@Slf4j
@Controller
@SessionAttributes
public class BlogHomeHandler {

    /**
     * 文章service
     */
    @Resource
    private ArticleServiceInter articleServiceInter;


    /**
     * 文章分类service
     */
    @Resource
    private CategoryServiceInter categoryServiceInter;

    /**
     * 首先显示的文章数量
     */
    @Value("${home.article-page-num}")
    private final int pageCommNum=5;

    /**
     * 异常捕获器
     * @param throwable 异常
     */
    @ExceptionHandler
    public void exceptionHandler(Throwable throwable){

        throwable.printStackTrace();
    }

    @RequestMapping({"/index.action","/"})
    public String index(String page, String categoryId, HttpServletRequest request) throws SQLException {
        log.debug("每页数量:"+pageCommNum);
        int currPage = 1;
        if (page != null && !page.equals("0")) {
            currPage = Integer.parseInt(page);
        }
        //总页数
        double totalPage = articleServiceInter.getArticlesCount(categoryId);
        totalPage=Math.ceil(totalPage/pageCommNum);
        //获取数据库文章数据
        //request.getServletContext().getRealPath("/")

        List<Article> articles = articleServiceInter.getArticles(currPage, categoryId);
        //表示当前显示的类别
        if (categoryId == null) {
            request.setAttribute("currCategoryId", -1);
        } else {
            request.setAttribute("currCategoryId", categoryId);
        }
        //第一页显示 分类信息
        if (currPage == 1) {
            List<Category> categories = categoryServiceInter.selectByAll(new HashMap<>(1));
            request.setAttribute("categories", categories);
        }
        if (articles != null && articles.size() > 0) {
            request.setAttribute("currPage", currPage);
            request.setAttribute("articles", articles);
        } else {//没获取到数据，当前页面不改变
            request.setAttribute("currPage", currPage - 1);
        }
        request.setAttribute("nextPage", currPage + 1);
        request.setAttribute("totalPage", totalPage);

        return "/home";
    }
}
