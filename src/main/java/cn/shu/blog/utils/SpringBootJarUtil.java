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
 * Jar包工具，获取当前Jar绝对目录...
 *
 * @author SXS
 * @作者 舒新胜
 * @项目 blog
 * @创建时间 2020-5-23 17:18
 */
@Component
public class SpringBootJarUtil {

    /**
     * 判断springboot jar包静态资源文件是否存在
     *
     * @param filePath 文件目录
     * @return {@code true} 文件存在
     */
    public boolean sourceExists(String filePath) {
        return getSource(filePath).exists();
    }


    /**
     * 中文会报错 NotFoundException
     * 获取springboot jar包静态资源文件Source对象
     *
     * @param filePath 文件目录
     * @return 资源对象
     */
    public Resource getSource(String filePath) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource("classpath:static" + filePath);
    }


    /**
     * 获取springboot jar包静态资源文件File对象
     *
     * @param filePath 文件目录
     * @return 文件对象
     */
    public File getSourceFile(String filePath) throws IOException {
        return getSource(filePath).getFile();
    }


    /**
     *
     * 获取配置文件的外部静态资源路径
     * 静态变量需要set方法注入
     */
    private static String extStaticSourcesPath = null;

    @Value("${extStaticSourcesPath}")
    public void setExtStaticSourcesPath(String extStaticSourcesPath) {
        SpringBootJarUtil.extStaticSourcesPath = extStaticSourcesPath;
    }


    /**
     * 获取配置的springboot jar包外部的资源文件目录
     *
     * @return 资源文件目录绝对路径
     */
    public String getExtStaticSources() throws FileNotFoundException {
        return getJarPath() + extStaticSourcesPath;
    }

    /**
     * 获取spring boot jar包当前目录 绝对路径
     *
     * @return jar包绝对路径
     * @throws FileNotFoundException 找不到文件
     */
    public String getJarPath() throws FileNotFoundException {
        String jarPath;
        if (Pdf2Swf.isWindowsSystem()) {
            //去掉 /E://前面的 /
            jarPath = ResourceUtils.getURL("").getPath().substring(1);
        } else {
            //Linux系统 前面 需要有 /表示绝对路径 否则找不到路径
            jarPath = ResourceUtils.getURL("").getPath();
        }
        return (jarPath == null || "null".equals(jarPath)) ? "" : jarPath;

    }
}
