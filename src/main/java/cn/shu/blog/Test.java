package cn.shu.blog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者 舒新胜
 * @项目 blog
 * @创建时间 2020-5-28 14:47
 */
public class Test {
    public static void main(String[] args)  {

        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            int y=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Document document = null;
                    try {
                        document = Jsoup.connect("http://localhost:8080").data("query", "Java")//请求参数
                                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                                .timeout(5000)
                                .get();


                        document = Jsoup.connect("http://localhost:8080/article/detail/"+y+".action").data("query", "Java")//请求参数
                                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                                .timeout(5000)
                                .get();
                        String xmlSource = document.toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }

    }
    @org.junit.Test
    public void test01(){

    }

}
