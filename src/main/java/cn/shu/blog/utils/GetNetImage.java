package cn.shu.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.ClassUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:
 * @Date:2018/5/22
 */
@Slf4j
public class GetNetImage {


    public static void main(String[] args) throws Exception {
        String tempPath = "D:/pictures/";

        System.out.println("输入爬取关键字（可用空格，、号分隔多个想爬的关键字）：");
        Scanner KeyWord = new Scanner(System.in);
        String Word = KeyWord.nextLine();
        List<String> keywordList = nameList(Word);
        for (String keyword : keywordList) {
            tempPath = tempPath + keyword + File.separator;
            File f = new File(tempPath);
            if (!f.exists()) {
                boolean mkdirs = f.mkdirs();
                if (!mkdirs){
                    return;
                }
            }
            getPictures( keyword,1, tempPath,null); //1代表下载一页，一页一般有30张图片
        }

    }


    public static File getPictures(String keyword,int max, String tempPath,String downloadName) { // key为关键词,max作为爬取的页数
        if (!tempPath.endsWith(File.separator)) {
            tempPath = tempPath + File.separator;
        }
        String finalURL = "";
        File returnFile=null;
            int picCount = 1;
            for (int page = 0; page <= max; page++) {
                log.info(keyword+":正在下载第" + page + "页面");
                Document document = null;
                try {
                    String url = "http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&word=" + keyword + "&cg=star&pn=" + page * 30 + "&rn=30&itg=0&z=0&fr=&width=&height=&lm=-1&ic=0&s=0&st=-1&gsm=" + Integer.toHexString(page * 30);
                    url="http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word="+keyword+"&pn="+page * 30;
                    document = Jsoup.connect(url).data("query", "Java")//请求参数
                            .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                            .timeout(5000)
                            .get();
                    String xmlSource = document.toString();
                   /* System.out.println(xmlSource);*/
                    String reg = "objURL\":\"http://.+?\\.jpg";
                    Pattern pattern = Pattern.compile(reg);
                    Matcher m = pattern.matcher(xmlSource);

                    while (m.find()) {
                        finalURL = m.group().substring(9);
                     /*   System.out.println(finalURL);*/
                        log.info(keyword + picCount++ + ":下载中" );
                        returnFile = download(finalURL, tempPath,downloadName);
                        if (returnFile != null) {
                            delMultyFile(tempPath);
                            return returnFile ;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        delMultyFile(tempPath);
        return returnFile;
    }

    public static void delMultyFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("File \"" + path + "\" NotFound when excute the method of delMultyFile()....");
        }
        File[] fileList = file.listFiles();
        File tempFile = null;
        if (fileList==null) return;
        for (File f : fileList) {
            if (f.isDirectory()) {
                {
                    delMultyFile(f.getAbsolutePath());
                }
            } else {
                if (f.length() == 0) {
                    log.debug(f.delete() + "---" + f.getName());
                }
            }
        }
    }

    public static List<String> nameList(String nameList) {
        List<String> arr = new ArrayList<String>();
        String[] list;
        if (nameList.contains(",")) {
            list = nameList.split(",");
        } else if (nameList.contains("、")) {
            list = nameList.split("、");
        } else if (nameList.contains(" ")) {
            list = nameList.split(" ");
        } else {
            arr.add(nameList);
            return arr;
        }
        arr.addAll(Arrays.asList(list));
        return arr;
    }


    //根据图片网络地址下载图片
    public static File download(String url, String path,String downloadName) {
        //path = path.substring(0,path.length()-2);
        File file = null;
        File dirFile = null;
        FileOutputStream fos = null;
        HttpURLConnection httpCon = null;
        URLConnection con = null;
        URL urlObj = null;
        InputStream in = null;
        byte[] size = new byte[1024];
        int num = 0;
        try {
            if (downloadName==null){
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
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {

            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {

            }


        }
        return null;
    }

    private static double getImageWH(String url) throws IOException {
        URLConnection con = null;
        HttpURLConnection httpCon = null;
        InputStream in = null;
        try {
            URL urlObj = new URL(url);
            con = urlObj.openConnection();
            httpCon = (HttpURLConnection) con;
            in = httpCon.getInputStream();
            //
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