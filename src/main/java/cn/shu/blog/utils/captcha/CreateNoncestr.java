package cn.shu.blog.utils.captcha;
/**
 * @author 作者
 * @version 创建时间：2019年1月26日 下午3:51:58
 * 类说明
 */


import java.util.Random;

public class CreateNoncestr {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}