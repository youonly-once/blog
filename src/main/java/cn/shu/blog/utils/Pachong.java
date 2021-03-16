package cn.shu.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:
 * @Date:2018/5/22
 */
@Slf4j
public class Pachong {

    private static int n=0;
    private static  int videoCount = 1;
    public static void main(String[] args) throws Exception{
        String downloadPath = "D:\\videos\\";

         log.info("输入爬取关键字（可用空格，、号分隔多个想爬的关键字）：");
        Scanner KeyWord = new Scanner(System.in);
        String Word =KeyWord.nextLine();
        List<String> list = nameList(Word);
        getPictures(list,160,downloadPath); //1代表下载一页，一页一般有30张图片
    }


    public static void getPictures(List<String> keywordList, int max,String downloadPath) throws Exception{ // key为关键词,max作为爬取的页数
        //String gsm=Integer.toHexString(max)+"";
        String finalURL = "";
        String tempPath = "";
        for(String keyword : keywordList){
            tempPath = downloadPath;
            if(!tempPath.endsWith("\\")){
                tempPath = downloadPath+"\\";
            }
            tempPath = tempPath+keyword+"\\";
            File f = new File(tempPath);
            if(!f.exists()){
                boolean b = f.mkdirs();
                if (!b){
                    sop("目录创建失败");
                    return;
                }
            }

            for(int page=30;page<=max;page++) {
                sop("正在下载第"+page+"页面");
                try {
                    final String url="https://5brv.com/arc/mainland/list_1_"+page+".html";
                    sop(url);
                    Document document = Jsoup.connect(url).data()//请求参数
                            .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                            .timeout(5000)
                            .get();
                    String xmlSource = document.toString();
                    xmlSource = StringEscapeUtils.unescapeHtml3(xmlSource);

                  //  String reg="href=\"/html/[^/]+/\"";
                    String reg="/arc/.*\\.html#m";
                    Pattern pattern = Pattern.compile(reg);
                    Matcher m = pattern.matcher(xmlSource);

                    while (m.find()) {
                        finalURL=m.group();
                        //final String fur=finalURL.substring("href=\"".length(),finalURL.length()-1);
                        final  String fur=finalURL;
                       new Thread(() -> {
                            try {
                                //草榴
                                //findVideoUrl("https://caoth.com"+fur);
                                //5G
                                findVideoUrl("https://5brv.com/"+fur);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                } catch (IOException e) {
                    sop("下载失败:"+e.getMessage());
                }
            }
        }
    }
    private static  void findVideoUrl(String url) throws IOException {
        synchronized(Pachong.class){
            while(n>=100){
                try {
                    Pachong.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        n++;
        sop("视频页面地址："+url);
        Document document = Jsoup.connect(url).data()//请求参数
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                .timeout(5000)
                .get();
        String xmlSource = document.toString();
        xmlSource = StringEscapeUtils.unescapeHtml3(xmlSource);
       // sop("结果"+xmlSource);
        String reg = "http[s]?://.+\\.mp4";

        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(xmlSource);
        while (m.find()){
            String finalUrl=m.group();
            sop(videoCount+++".视频下载地址:"+finalUrl);
            download(finalUrl,"D:\\video\\");
            //download("https://x.wuxoo4.com/mp4/2dfb63ce4bf345c9b738e47bdbcf8503.mp4","D:\\video\\");
        }
        synchronized(Pachong.class){
            n--;
            Pachong.class.notifyAll();
        }

    }
    public static void delMultyFile(String path){
        File file = new File(path);
        if(!file.exists())
        { throw new RuntimeException("File \""+path+"\" NotFound when excute the method of delMultyFile()....");}
        File[] fileList = file.listFiles();
        File tempFile=null;
        for(File f : fileList){
            if(f.isDirectory()){
                { delMultyFile(f.getAbsolutePath());}
            }else{
                if(f.length()==0)
                { sop(f.delete()+"---"+f.getName());}
            }
        }
    }
    public static List<String> nameList(String nameList){
        List<String> arr = new ArrayList<String>();
        String[] list;
        if(nameList.contains(","))
        { list= nameList.split(",");}
        else if(nameList.contains("、"))
        { list= nameList.split("、");}
        else if(nameList.contains(" "))
        {list= nameList.split(" ");}
        else{
            arr.add(nameList);
            return arr;
        }
        for(String s : list){
            arr.add(s);
        }
        return arr;
    }
    public static void sop(Object obj){

    }
    //根据图片网络地址下载图片
    public static void download(String url, String path){
        File file= null;
        File dirFile=null;
        FileOutputStream fos=null;
        HttpURLConnection httpCon = null;
        URLConnection con = null;
        URL urlObj=null;
        InputStream in =null;
        BufferedInputStream bin=null;
        BufferedOutputStream bfos=null;
        byte[] buffer= new byte[1024];
        try {
            String downloadName= url.substring(url.lastIndexOf("/")+1);
            dirFile = new File(path);
            if(!dirFile.exists() && path.length()>0){
                if(dirFile.mkdir()){
                    sop("creat document file \""+path.substring(0,path.length()-1)+"\" success...\n");
                }
            }else{

                if(url.startsWith("http") || url.startsWith("https")){
                    //已经下载过
                    file = new File(path+downloadName);
                    if (file.exists()){
                        return;
                    }
                    //输入流
                    urlObj = new URL(url);
                    con = urlObj.openConnection();
                    httpCon =(HttpURLConnection) con;
                    in = httpCon.getInputStream();
                    bin=new BufferedInputStream(in);

                    //输出流
                    fos = new FileOutputStream(file);
                    bfos=new BufferedOutputStream(fos);
                    sop("开始下载:"+path+downloadName);
                    int num=0;
                    while((num=bin.read(buffer)) != -1){
                        bfos.write(buffer,0,num);

                    }
                }

            }
            sop("下载成功:"+path+downloadName);
        }catch (FileNotFoundException | NullPointerException notFoundE) {
            sop("找不到该网络图片....");
        } catch(IOException ioE){
            sop("产生IO异常.....");
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bfos != null) {
                    bfos.close();
                }
                if (in != null) {
                    in.close();
                }
                if (bin != null) {
                    bin.close();
                }
            } catch (Exception e) {

            }
        }

    }
    private static double getImageWH(String url) throws IOException {
        URLConnection con=null;

        HttpURLConnection httpCon=null;

        InputStream in=null;
        try{


            URL urlObj = new URL(url);

            con = urlObj.openConnection();
            httpCon = (HttpURLConnection) con;
            in = httpCon.getInputStream();
            //
            BufferedImage r = ImageIO.read(in);
            double height = r.getHeight();

            double width = r.getWidth();
            double wh=width/height;
            return wh;
        }finally {

            if (in != null) {
                in.close();
            }
        }

    }
}