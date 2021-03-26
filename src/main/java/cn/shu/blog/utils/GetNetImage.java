package cn.shu.blog.utils;

import com.zaxxer.hikari.util.UtilityElf;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:
 * @Date:2018/5/22
 */
@Slf4j
public class GetNetImage {

    /**
     * 爬取网络图片（百度搜索图片）
     *
     * @param keyword      爬取关键字
     * @param max          爬取最大次数（百度搜索图片的页数，下载多少页，一页一般有30张图片）
     * @param tempPath     图片保存目录
     * @param downloadName 文件名
     * @return 下载的文件
     */
    public static File getNetImg(String keyword, int max, String tempPath, String downloadName) { // key为关键词,max作为爬取的页数
        if (!tempPath.endsWith(File.separator)) {
            tempPath = tempPath + File.separator;
        }

        int picCount = 1;
        for (int page = 0; page <= max; page++) {
            log.info(keyword + ":正在下载第" + page + "页面");
            try {
                //爬取地址
                String url = "http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=" + keyword + "&pn=" + page * 30;
                //请求参数
                Document document = Jsoup.connect(url).data("query", "Java")
                        .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                        .timeout(5000)
                        .get();
                //获取网页源码
                String xmlSource = document.toString();
                //正则匹配网页源码中图片的地址
                String reg = "objURL\":\"http://.+?\\.jpg";
                Pattern pattern = Pattern.compile(reg);
                Matcher m = pattern.matcher(xmlSource);
                //循环下载网页中的图片
                while (m.find()) {
                    String finalUrl = m.group().substring(9);
                    log.info(keyword + picCount++ + ":下载中");
                    File returnFile = download(finalUrl, tempPath, downloadName);
                    if (returnFile != null) {
                        //下载成功返回
                        delMultiFile(tempPath);
                        return returnFile;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        delMultiFile(tempPath);
        return null;
    }

    /**
     * 删除生成的临时文件
     *
     * @param path 根目录
     */
    private static void delMultiFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        File[] fileList = file.listFiles();
        if (fileList == null) {
            return;
        }
        for (File f : fileList) {
            if (f.isDirectory()) {
                delMultiFile(f.getAbsolutePath());
            } else {
                if (f.length() == 0) {
                    log.debug(f.delete() + "---" + f.getName());
                }
            }
        }
    }


    /**
     * 下载网络图片
     *
     * @param url          图片地址
     * @param path         保存目录
     * @param downloadName 文件名
     * @return 下载的文件
     */
    public static File download(String url, String path, String downloadName) {
        File file;
        File dirFile;
        FileOutputStream fos = null;
        HttpURLConnection httpCon;
        URLConnection con;
        URL urlObj;
        InputStream in = null;
        byte[] size = new byte[1024];
        int num;
        try {
            if (downloadName == null) {
                downloadName = url.substring(url.lastIndexOf(File.separator) + 1);
            }

            dirFile = new File(path);
            if (!dirFile.exists() && path.length() > 0) {
                if (dirFile.mkdir()) {
                    log.info("creat document file \"" + path.substring(0, path.length() - 1) + "\" success...\n");
                }
            } else {

                if (url.startsWith("http")) {
                    urlObj = new URL(url);

                    con = urlObj.openConnection();
                    httpCon = (HttpURLConnection) con;
                    in = httpCon.getInputStream();
                    double wh = getImageWH(url);

                    //先判断是否符合要求，看图片是否符合要求 符合停止下载
/*                    if (height>140 && height<160){
                        if (width>210 && width<230){*/
                    //if (wh >1.4 && wh<1.8){

                    file = new File(path + downloadName);
                    fos = new FileOutputStream(file);
                    while ((num = in.read(size)) != -1) {
                        for (int i = 0; i < num; i++) {
                            fos.write(size[i]);
                        }
                    }
                    //  }
                    return file;
                }

            /*        }
                }*/
            }
        } catch (FileNotFoundException | NullPointerException notFoundE) {
            log.debug("找不到该网络图片....");
        } catch (IOException ioE) {
            log.debug("产生IO异常.....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }
        return null;
    }

    /**
     * 获取网络图片的宽高比
     * @param url 图片地址
     * @return 宽高比
     * @throws IOException 连接失败
     */
    private static double getImageWH(String url) throws IOException {
        URLConnection con;
        HttpURLConnection httpCon;
        InputStream in = null;
        try {
            URL urlObj = new URL(url);
            con = urlObj.openConnection();
            httpCon = (HttpURLConnection) con;
            in = httpCon.getInputStream();
            BufferedImage r = ImageIO.read(in);
            double height = r.getHeight();

            double width = r.getWidth();
            return width / height;

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {

            }
        }
    }
}