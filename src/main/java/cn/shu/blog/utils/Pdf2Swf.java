package cn.shu.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
@Slf4j
public class Pdf2Swf {


    /**
     * 利用SWFTools工具将pdf转换成swf，转换完后的swf文件与pdf同名
     * @param inputPath PDF文件存放路径（包括文件名）
     * @param exePath 转换器安装路径
     */
    public static synchronized boolean pdf2swf(String inputPath , String outputPath, String exePath)  {
        // 源文件不存在则返回
        File source = new File(inputPath);
        if (!source.exists()) {
            log.error("原始文件不存在!!"+inputPath);
            return false;
        }
        // 目标路径存在则删除
        File out = new File(outputPath);
        if (out.exists()) {
            log.info("目标文件存在,删除!!");
            out.delete();
        }

        Process pro = null;
        if (isWindowsSystem()) {
            //如果是windows系统
            //命令行命令
            String cmd = exePath + " \"" + inputPath + "\" -o \"" + outputPath + "\"" + " -f -T 9";

            //Runtime执行后返回创建的进程对象
            try {
                pro = Runtime.getRuntime().exec(cmd);
                log.info("window下开始转换!! "+ cmd);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("转换失败!! 异常: "+ e );
                return false;
            }
        } else {
            //如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程
            String[] cmd = new String[5];
            cmd[0] = exePath;
            cmd[1] = inputPath;
            // cmd[2] = filePath + "/" + fileName + ".swf";
            cmd[3] = outputPath;
            //Runtime执行后返回创建的进程对象
            try {
                pro = Runtime.getRuntime().exec(cmd);
                log.info("linux下开始转换!! "+ pro);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("转换失败!! 异常: "+ e );
                return false;
            }
        }
        //非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
        new DoOutput(pro.getInputStream()).start();
        new DoOutput(pro.getErrorStream()).start();
        try {
            //调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
            pro.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("转换失败!! 异常: "+ e );
            return false;
        }finally {
            pro.destroy();
        }
        return true;
    }

    /**
     * 判断是否是windows操作系统
     * @return
     */
    public static boolean isWindowsSystem() {
        String p = System.getProperty("os.name");
        return p.toLowerCase().contains("windows");
    }

    /**
     * 多线程内部类
     * 读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流，进程将死锁
     */
    private static class DoOutput extends Thread {
        public InputStream is;
        //构造方法
        public DoOutput(InputStream is) {
            this.is = is;
        }
        @Override
        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
            String str = null;
            try {
                //这里并没有对流的内容进行处理，只是读了一遍
                while ((str = br.readLine()) != null);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 测试main方法
     * @param args
     */
    public static void main(String[] args) {
        //转换器安装路径
        String exePath = "C:\\Program Files (x86)\\SWFTools/pdf2swf.exe";
        boolean b = Pdf2Swf.pdf2swf("E:\\JAVA\\WEB\\个人笔记\\2020-04-11_JDBC.pdf","E:\\JAVA\\WEB\\个人笔记\\2020-04-11_JDBC.swf" , exePath);
        System.out.println(b);
    }
}

