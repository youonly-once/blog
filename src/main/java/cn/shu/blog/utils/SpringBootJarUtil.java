package cn.shu.blog.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @作者 舒新胜
 * @项目 blog
 * @创建时间 2020-5-23 17:18
 */
@Component
public class SpringBootJarUtil {

    /**
     * 判断springboot jar包静态资源文件是否存在
     * @param filePath
     * @return
     */
    public  boolean sourceExists(String filePath){

        return getSource(filePath).exists();
    }
    //中文会报错 notfoundexception

    /**
     *获取springboot jar包静态资源文件Source对象
     * @param filePath
     * @return
     */
    public  Resource getSource(String filePath){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:static"+filePath);
        return resource;
    }
    /**
     *获取springboot jar包静态资源文件File对象
     * @param filePath
     * @return
     */
    public  File getSourceFile(String filePath) throws IOException {
        return getSource(filePath).getFile();
    }
    //获取配置文件的外部静态资源路径
    //静态变量需要set方法赋值
    private static String extStaticSourcesPath=null;
    @Value("${extStaticSourcesPath}")
    public void setExtStaticSourcesPath(String extStaticSourcesPath){
        SpringBootJarUtil.extStaticSourcesPath=extStaticSourcesPath;
    }
    /**
     * 获取配置的springboot jar包外部的资源文件目录
     * @return
     */
    public  String getExtStaticSources() throws FileNotFoundException {
        String finalPath= getJarPath()+extStaticSourcesPath;
        return finalPath;
    }

    /**
     * 获取spring boot jar包当前目录 绝对路径
     * @return
     * @throws FileNotFoundException
     */
    public  String getJarPath() throws FileNotFoundException {
        String jarPath="";
        if(Pdf2Swf.isWindowsSystem()){
            //去掉 /E://前面的 /
            jarPath=ResourceUtils.getURL("").getPath().substring(1);
        }else{
            //Linux系统 前面 需要有 /表示绝对路径 否则找不到路径
            jarPath=ResourceUtils.getURL("").getPath();
        }
        return (jarPath==null||jarPath.equals("null"))?"":jarPath;

    }
}
