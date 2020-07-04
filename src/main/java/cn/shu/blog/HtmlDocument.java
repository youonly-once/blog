package cn.shu.blog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者 舒新胜
 * @项目 blog
 * @创建时间 2020/6/6 18:18
 */
public class HtmlDocument {

    /**
     * 读取Html文件的内容
     * @param filePath 文件路径
     * @param length 读取长度
     * @return
     */
    public static String readHtml(String filePath,int length) {
        StringBuilder resultBuilder=new StringBuilder();
        FileInputStream fi=null;
        BufferedInputStream bi=null;
        try {
            fi=new FileInputStream(new File(filePath));
            bi=new BufferedInputStream(fi);
            int readLen;
            byte[] bytes=new byte[1024];

            StringBuilder stringBuilder=new StringBuilder();
            while ((readLen=bi.read(bytes,0,1024))!=-1){
                String  str= new String(bytes,0,readLen, StandardCharsets.UTF_8);
                stringBuilder.append(str.replaceAll("\\s",""));
            }
            //System.out.println(stringBuilder);
            //查找p标签中的内容
            Pattern pattern=Pattern.compile("<[pPhH][1]?>[^<>]+</[pPhH][1]?>");
            Matcher matcher = pattern.matcher(stringBuilder);
/*            boolean b= matcher.matches();
            System.out.println(b);
            System.out.println(b);*/


            //循环取出
            int i=1;
            while (matcher.find()){
                if (i==4){//只显示3行
                    break;
                }
                String group = matcher.group();
                // System.out.println(group);
                //获取p中的内容
                String substring="";
                if (group.contains("<p>")) {
                    substring = group.substring("<p>".length(), group.length() - "</p>".length());
                }else{
                    substring = group.substring(4, group.length() - 5);
                }
                resultBuilder.append(substring);
                if (i!=3){
                    resultBuilder .append("<br/>");
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fi!=null){
                    fi.close();
                }
                if (bi!=null){
                    bi.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //System.out.println(stringBuilder.toString());
        //获取200个
        if (resultBuilder.length()>length){
            return resultBuilder.toString().substring(0,length);
        }else{
            return resultBuilder.toString();
        }

    }
}
