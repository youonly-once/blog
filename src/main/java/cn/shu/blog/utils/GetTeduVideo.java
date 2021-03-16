package cn.shu.blog.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-16 11:18
 */
public class GetTeduVideo {
    private static String m3u8Url="http://c.it211.com.cn/big2002%s/big2002%s.m3u8";
    private static int n=0;
    private static  int videoCount = 1;
    public static void main(String[] args) throws Exception{
        //http://c.it211.com.cn/big20020515am/big20020515am.m3u8

        String m3u8UrlPM="http://c.it211.com.cn/big2002%spm/big2002%spm.m3u8";

        String beginDateStr="0227";
        String endDateStr="0515";
        String currDateStr=beginDateStr;
        do {
             a(currDateStr,"am");
            a(currDateStr,"pm");
            //下一天
            currDateStr=getAddDay(currDateStr);
        }while (!currDateStr.equals(endDateStr));

    }
    public static void a(String currDateStr,String time) throws IOException, ParseException {
        String downloadPath="F:/达内视频/"+currDateStr+File.separator+time;
        //标识
        String str=currDateStr+time;
        //最终下载地址
        String finalUrl = String.format(m3u8Url, str, str);
        //下载的文件名
        String fileName=str+".m3u8";
        downloadM3U8(finalUrl,downloadPath,fileName);

    }
    public static void downloadM3U8(String finalUrl,String path,String fileName) throws IOException {
        File m3u8File= new File(path+"/"+fileName);
        boolean download = download(finalUrl, path, m3u8File);
        //下载TS文件
        if (!download){
           return;
        }
        FileReader fileReader =null;
        BufferedReader bufferedReader=null;
        try {
            String tsPath = path + "/ts";
           fileReader = new FileReader(m3u8File);
            bufferedReader = new BufferedReader(fileReader);
            String tsUrl;
            while ((tsUrl = bufferedReader.readLine()) != null) {
                if (tsUrl.startsWith("#")) {
                    int httpPos = tsUrl.indexOf("http");
                    if (httpPos==-1){
                        continue;
                    }
                    tsUrl=  tsUrl.substring(httpPos,tsUrl.length()-1);
                }
                String tsFileName = tsUrl.substring(tsUrl.lastIndexOf("/") + 1);
                File tsFile = new File(tsPath + "/" + tsFileName);
                download(tsUrl, tsPath, tsFile);
            }
        }finally {
            try{

                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch (IOException e){

            }

        }

    }
    public static String getAddDay(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMdd");
        Date beginDate = simpleDateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();// 当前日期
        calendar.setTime(beginDate);
        // 后一天凌晨 10-1 00:00 - 10.2 00:00
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        int year1 = calendar.get(Calendar.YEAR);// 年
        int month1 = calendar.get(Calendar.MONTH) + 1;// 月 0-11表示12月
        int dayAdd1 = calendar.get(Calendar.DAY_OF_MONTH);
        Date time = calendar.getTime();
        return simpleDateFormat.format(time);
    }



    //根据图片网络地址下载图片
    public static boolean download(String url, String path,File file){
        sop("下载地址:"+url);

        FileOutputStream fos=null;
        HttpURLConnection httpCon = null;
        URLConnection con = null;
        URL urlObj=null;
        InputStream in =null;
        BufferedInputStream bin=null;
        BufferedOutputStream bfos=null;
        byte[] buffer= new byte[1024];
        try {
            //创建文件保存目录
            File dirPath=new File(path);
            if (!dirPath.exists()){
                boolean mkdirs = dirPath.mkdirs();
                if (!mkdirs){
                    throw new IOException("创建目录失败");
                }
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
                    int num=0;
                    while((num=bin.read(buffer)) != -1){
                        bfos.write(buffer,0,num);

                    }
               return true;



        }catch (FileNotFoundException | NullPointerException notFoundE) {
            sop("找不到该网络图片....");
            notFoundE.printStackTrace();
        } catch(IOException ioE){
            sop("产生IO异常.....");
            ioE.printStackTrace();
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
        return false;

    }
    public static void sop(Object obj){

    }

}
