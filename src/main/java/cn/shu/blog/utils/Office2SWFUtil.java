package cn.shu.blog.utils;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * 2014-7-24
 * @author DTF
 */
public class Office2SWFUtil  implements Serializable{

    private String  pdf2swfTool= "D:/Program Files/SWFTools/pdf2swf.exe";

    private static final long serialVersionUID = 8410551332651163557L;



    private String fileType = ".doc;.xls;.ppt;.docx;.xlsx;.pptx";
    /**
     * office file(.doc;.xls;.ppt;.docx;.xlsx;.pptx)
     * */
    private File officFile = null;
    /**
     * pdf file
     * */
    private File pdfFile = null;
    /**
     * swfFile
     * */
    private File swfFile = null;

    private File swfURL = null;
    /***
     * test1.doc ---> test1 file name
     * */
    private String abFileName = null;

    /**
     * test1.doc ---> doc
     * */
    private String bcFileName = null;

    /**
     * fileURL
     * */
    private String fileURL = null;

    public Office2SWFUtil(){

    }

    public Office2SWFUtil(String fileUrl){
        officFile = new File(fileUrl);
    }

    public void toChange() throws IOException{
        setFileURL(officFile.getParent()); //文件路径
        String fileName = officFile.getName(); //文件名
        setAbFileName(fileName.substring(0,fileName.lastIndexOf("."))); //文件真实的名称
        setBcFileName(fileName.substring(fileName.lastIndexOf("."),fileName.length())); //文件后缀
        if(officFile.exists()){ //判断文件是否存在
            if(isContext(this.getBcFileName())){ //判断是否是office 文件
                pdfFile = new File(getFileURL()+"/"+getAbFileName()+".pdf");
                swfURL = new File(getFileURL()+"/"+getAbFileName());
                if(!swfURL.exists()){
                    swfURL.mkdirs();
                }

               // DOC2PDFUtil dp = new DOC2PDFUtil(officFile, pdfFile);
              //  dp.run();
             //   PDF2SWFUtil.pdf2swf(getFileURL()+"/"+getAbFileName()+".pdf",getFileURL()+"/"+getAbFileName()+"/"+getAbFileName(), pdf2swfTool);
                new File(getFileURL()+File.separator+getAbFileName()+".pdf").delete();
            }else if(this.getBcFileName().equals(".pdf")){ //判断是否是 pdf文件
              //  PDF2SWFUtil.pdf2swf(getFileURL()+"/"+getAbFileName()+".pdf",getFileURL()+"/"+getAbFileName()+"/"+getAbFileName(), pdf2swfTool);
            }
        }
    }

    private boolean isContext(String bcFileName){
        boolean flag = false;
        if(bcFileName != null && !"".endsWith(bcFileName)){
            String[] typeList = fileType.split(";");
            for(String type : typeList){

                if(type.equals(bcFileName)){
                    return true;
                }
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        try {
            new Office2SWFUtil("D:/temp/1.docx").toChange();
            new Office2SWFUtil("D:/temp/test1.xls").toChange();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public File getOfficFile() {
        return officFile;
    }

    public void setOfficFile(File officFile) {
        this.officFile = officFile;
    }

    public File getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(File pdfFile) {
        this.pdfFile = pdfFile;
    }

    public File getSwfFile() {
        return swfFile;
    }

    public void setSwfFile(File swfFile) {
        this.swfFile = swfFile;
    }

    public String getAbFileName() {
        return abFileName;
    }

    public void setAbFileName(String abFileName) {
        this.abFileName = abFileName;
    }

    public String getBcFileName() {
        return bcFileName;
    }

    public void setBcFileName(String bcFileName) {
        this.bcFileName = bcFileName;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public File getSwfURL() {
        return swfURL;
    }

    public void setSwfURL(File swfURL) {
        this.swfURL = swfURL;
    }

}