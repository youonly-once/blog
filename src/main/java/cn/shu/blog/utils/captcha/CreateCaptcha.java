package cn.shu.blog.utils.captcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//http://cqgtweixin.natapp1.cc/makeSuggestion/CaptchaServerlet
public class CreateCaptcha extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    char[] codeSequence1 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    char[] codeSequence = { '2', '3', '4', '5', '6', '7', '8', '9' };

    public String getCode(ServletOutputStream sos) throws IOException {
         int width = 110;//验证码宽度
        int height = 45;//验证码高度
         int codeCount = 4;//验证码个数
        int lineCount = 5;//混淆线个数
        //定义随机数类
        Random r = new Random();
        //定义存储验证码的类
        StringBuilder builderCode = new StringBuilder();
        //定义画布
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics g = buffImg.getGraphics();
        //1.设置颜色,画边框
        g.setColor(Color.GRAY);
        g.drawRect(0,0,width,height);
        //2.设置颜色,填充内部
        g.setColor(Color.GRAY);
        // g.setColor(new Color(207, 226, 243));
        g.fillRect(1,1,width-2,height-2);
        //3.设置干扰线
        g.setColor(Color.GREEN);

        for (int i = 0; i < lineCount; i++) {
            g.drawLine(r.nextInt(width),r.nextInt(width),r.nextInt(width),r.nextInt(width));
        }
        //4.设置验证码
        g.setColor(Color.GREEN);
        //4.1设置验证码字体
        g.setFont(new Font("隶书",Font.LAYOUT_LEFT_TO_RIGHT,35));
        for (int i = 0; i < codeCount; i++) {
            char c = codeSequence[r.nextInt(codeSequence.length)];
            builderCode.append(c);
            g.drawString(c+"",8+(15*i),36);
        }
        //5.输出到屏幕
        ImageIO.write(buffImg,"png",sos);
        //8.关闭sos
        sos.close();
        return builderCode.toString();


    }
}
