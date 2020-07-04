package cn.shu.blog.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 通过反射将ResultSet转Bean，类型及字段名必须与数据库严格一致
 */
public class SearchArticle {
    //当前第几页
    private int currPage;
    //总页数
    private int totalNum;
    //搜索到的文章列表
    private List<Article> articles;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }




    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "SearchArticle{" +
                "currPage=" + currPage +
                ", totalNum=" + totalNum +
                ", articles=" + articles +
                '}';
    }
}
