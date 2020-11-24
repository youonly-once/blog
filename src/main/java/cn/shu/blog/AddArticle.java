package cn.shu.blog;

import cn.shu.blog.utils.*;
import cn.shu.blog.utils.database.JDBCUtil;
import cn.shu.blog.utils.lucene.LuceneUtilForArticle;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-21 20:34
 */
@Component
@Slf4j
public class AddArticle {

    private static String swfToolsPath = "D:/SWFTools/pdf2swf.exe";
    private static String rootPath = "E:\\JAVA\\project_idea\\blog\\ext_resources";
    @Value("${swfToolsPath}")
    public void setSwfToolsPath(String swfToolsPath) {
        AddArticle.swfToolsPath = swfToolsPath;
    }


    public static void main(String[] args) {
       addFileInfo(rootPath, "/note");
        //创建索引
        LuceneUtilForArticle.createIndex(rootPath);
    }


    public static void addFileInfo(String path, String docDir) {
        //获取该路径下的所有文件
        File dir = new File(path + docDir);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file :files) {
            if (file.isFile()) {
                try {
                    saveFileInfo(path, file);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if (file.isDirectory()){
                addFileInfo(file.getAbsolutePath(),"/");
            }
        }
    }

    private static void saveFileInfo(String path, File file) {

        String sql = "insert into article(id,TITLE,createDate,updateDate,categoryId,userId,visitors,fileType,targetFilePath,sourceFilePath,description,imagePath) " +
                "values(null,'%s','%s','%s','%s',33,0,'%s','%s','%s','%s','%s');";

        /**2020-04-22_后端_JAVA_常用设计模式
         *按_分隔 第一部分为创建日期[0]
         * 第一部分为category [1]
         * 第三部分为tag     [2]
         * 第四部分为标题 [4]
         */
        //获取文件名 不包括扩展名
        String filenameAndExt = file.getName();
        int pointLoc = filenameAndExt.lastIndexOf(".");
        String filename = filenameAndExt.substring(0, pointLoc);
        //获取扩展名
        String ext = filenameAndExt.substring(pointLoc + 1).trim();

        //分割
        String[] fileInfo = filename.split("_");

        //创建日期
        String createDate = fileInfo[0].trim();
        //分类

        //标签
        String tag = fileInfo[2].trim();
        long categoryId = insertCategoryName(tag);
        //文件类型
        String fileType = "." + ext;
        //标题
        String title = fileInfo[3].trim();
        //最后修改时间
        String updateDate = DateUtil.formatDate(new Date(file.lastModified()), "yy-MM-dd");
        //源文件目录
        String sourceFilePath = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(rootPath) + rootPath.length());
        //目标文件目录 如果是docx或doc转为swf
        String targetFilePath = "/" + ext + "/" + filename + fileType;
        if ("docx".equalsIgnoreCase(ext) || "doc".equalsIgnoreCase(ext)) {
            fileType = ".swf";
            targetFilePath = "/swf/" + MD5.md5(filename) + fileType;
            //创建SWF文件
            turnSwf(path + sourceFilePath, path + targetFilePath);
        }
        //数据库已存在该文件 则不添加
        if (fileIsExist(sourceFilePath)) {
            return;
        }
        //描述读取文档内容
        String desc = "暂无描述";
        if ("docx".equalsIgnoreCase(ext) || "doc".equalsIgnoreCase(ext)) {
            desc = readWord(rootPath + sourceFilePath).trim();
        } else {
            desc = HtmlDocument.readHtml(rootPath + sourceFilePath, 200).trim();
            targetFilePath = sourceFilePath;
        }

        //展示图 以tag为标记
        String imgPath = "images/articles/" + tag + ".jpg";
        sql = String.format(sql, title, createDate, updateDate, categoryId, fileType, targetFilePath, sourceFilePath, desc, imgPath);
        //sqlAll.append();
        sql = sql.replace("\\", "/");
        insertFileInfo(sql);

    }

    private static boolean fileIsExist(String sourceFilePath) {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            conn = JDBCUtil.getConnection();
            pre = conn.prepareStatement("select id from article where sourceFilePath=?");
            pre.setString(1, sourceFilePath);
            res = pre.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
            //出现异常也只能 return true，不知道有没有，默认有不要覆盖
            return true;
        } finally {
            JDBCUtil.close(conn, pre, res);
        }
    }

    private static long insertCategoryName(String categoryName) {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            pre = conn.prepareStatement("INSERT IGNORE INTO category VALUES(NULL, ?);", Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, categoryName);
            pre.executeUpdate();
            //获取主键
            rs = pre.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                pre = conn.prepareStatement("SELECT * FROM category where categoryName=?");
                pre.setString(1, categoryName);
                rs = pre.executeQuery();
                if (rs.next()) {
                    return rs.getLong("id");
                }
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;

        } finally {
            JDBCUtil.close(conn, pre, rs);
        }

    }

    private static void insertFileInfo(String sql) {
        System.out.println(sql);
        Connection conn = null;
        Statement pre = null;
        try {
            conn = JDBCUtil.getConnection();
            pre = conn.createStatement();

            if (!StringUtil.isEmpty(sql)) {
                pre.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("保存文件信息到数据库错误:" + e.getMessage());

        } finally {
            JDBCUtil.close(conn, pre, null);
        }
    }

    public static boolean turnSwf(String pathDoc, String pathSwf) {
        System.out.println(pathDoc);
        System.out.println(pathSwf);
/*        String sourceFileName = sourceFilePath.substring(sourceFilePath.lastIndexOf(File.separator) + 1, sourceFilePath.lastIndexOf("."));
        String targetFileName = targetFilePath.substring(targetFilePath.lastIndexOf(File.separator) + 1, targetFilePath.lastIndexOf("."));
        String pathSwf = getServletContext().getRealPath("/swf") + "/" + targetFileName + ".swf";*/

        File file = new File(pathSwf);
        if (file.exists() || file.isDirectory()) {
            return true;
        }
        System.out.println("源DOC文件:" + pathDoc);

        String pathPdf = pathSwf.substring(0, pathSwf.lastIndexOf(".")) + ".pdf";
        file = new File(pathPdf);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("生成pdf文件:" + pathPdf);
            //docx转pdf
            Doc2Pdf.turn(pathDoc, pathPdf);
        }


        //pdf转swf
        System.out.println("转换swf中...");
        System.out.println("生成Swf文件:" + pathSwf);
        return Pdf2Swf.pdf2swf(pathPdf, pathSwf, swfToolsPath);
    }

    /**
     * 读取word文件内容
     *
     * @param path
     * @return buffer
     */
    public static String readWord(String path) {
        // TODO Auto-generated method stub
        StringBuilder content = new StringBuilder();
        InputStream istream = null;
        try {
            istream = new FileInputStream(path);
            XWPFDocument doc = new XWPFDocument(istream);
            //获取所有段落
            List<XWPFParagraph> paras = doc.getParagraphs();
            //遍历段落

            for (XWPFParagraph para : paras) {
                //当前段落的属性
                //CTPPr pr = para.getCTP().getPPr();
                //获取段落文本
                if (content.length() > 200) {
                    break;
                }
                content.append(para.getText().trim()).append(",");

            }
            return content.substring(0, 200);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("获取word文档内容错误:" + e.getMessage());
        } finally {
            if (istream != null)
                try {
                    istream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return content.toString();
    }

    public static boolean turnSwfJar(String pathDoc, String pathSwf) {
        try {
            //jar内部资源存在SWF文件
            if (SpringBootJarUtil.sourceExists(pathSwf)) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        log.warn("内部资源文件不存在:" + pathSwf);
        String pathPdf = pathSwf.replace(".swf", ".pdf");
        ;
        pathPdf = pathPdf.replace("/swf/", "/pdf/");
        try {
            pathDoc = SpringBootJarUtil.getExtStaticSources() + pathDoc;
            pathPdf = SpringBootJarUtil.getExtStaticSources() + pathPdf;
            pathSwf = SpringBootJarUtil.getExtStaticSources() + pathSwf;
            boolean mkdirs = new File(pathPdf).getParentFile().mkdirs();
            boolean mkdirs1 = new File(pathSwf).getParentFile().mkdirs();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        //jar外部资源swf文件存在
        File file = new File(pathSwf);
        if (file.exists() && !file.isDirectory()) {
            return true;
        }
        log.warn("外部资源文件不存在:" + pathSwf);
        log.info("源DOC文件:" + pathDoc);
        file = new File(pathPdf);
        if (!file.exists() && !file.isDirectory()) {
            log.info("生成pdf文件:" + pathPdf);
            //docx转pdf
            int b = Doc2Pdf.turn(pathDoc, pathPdf);
            if (b != 0) {
                log.error("生成pdf失败");
                return false;
            }
        }

        //pdf转swf
        log.info("转换swf中..." + pathSwf);
        boolean b = Pdf2Swf.pdf2swf(pathPdf, pathSwf, swfToolsPath);
        if (!b) {
            log.error("转换swf失败:" + pathSwf);
        }
        return b;
    }
}
