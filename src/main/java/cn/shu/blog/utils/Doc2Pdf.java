package cn.shu.blog.utils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.ConnectException;
@Slf4j
public class Doc2Pdf {
    public static void main(String[] args) {
        turn("E:\\JAVA\\WEB\\个人笔记\\2020-04-11_JDBC.docx","E:\\JAVA\\WEB\\个人笔记\\2020-04-11_JDBC.swf");

    }
    /**
     * 可以将一种类型的文档转化为另一种类型，例如：.doc 转为 .pdf
     *
     * @param sourcePath
     *            源文件路径
     * @param destPath
     *            目标文件路径
     * @return
     */
    public static int turn(String sourcePath, String destPath) {
            File inputFile = new File(sourcePath);
            if (!inputFile.exists()) {
                log.error("找不到源文件："+sourcePath);
                return -1;// 找不到源文件, 则返回-1
            }
            return turn(inputFile,destPath);
    }
    public static int turn(File inputFile, String destPath) {
        OpenOfficeConnection connection=null;

        try {

            File outputFile = new File(destPath);
            // 连接到openOffice服务
            connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();
            // 将源文件转换为目标文件
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);

            return 0;
        } catch (ConnectException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接服务
            if (connection != null) {
                connection.disconnect();
            }
        }
        return 1;
    }
}
