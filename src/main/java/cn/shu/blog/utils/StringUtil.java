package cn.shu.blog.utils;

public class StringUtil {

    private StringUtil() {
    }
    public static boolean isEmpty(String str){
        return str == null || str.trim().isEmpty();
    }
}
