package cn.shu.blog.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通过反射将ResultSet转Bean，类型及字段名必须与数据库严格一致
 */
public class Article{

    //文章分类
    private String categoryName;
    private int id;
    private String title;
    private Date createDate;
    private Date updateDate;
    private int categoryId;
    private int userId;
    private int visitors;
    private String fileType;
    private String targetFilePath;
    private String sourceFilePath;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String description;
    private String imagePath;
    private long commNum;
    private String nickname;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;//查找匹配度时的匹配次数
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTargetFilePath() {
        return targetFilePath;
    }

    public void setTargetFilePath(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreateDate() {

        return format(createDate);
    }
    private String format(Date date){
        if (date==null)return "";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Article{" +
                "categoryName='" + categoryName + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", visitors=" + visitors +
                ", fileType='" + fileType + '\'' +
                ", targetFilePath='" + targetFilePath + '\'' +
                ", sourceFilePath='" + sourceFilePath + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", commNum=" + commNum +
                ", nickname='" + nickname + '\'' +
                ", score=" + score +
                '}';
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {

        return format(updateDate);
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public void setUpdateDate(String updateDate) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.updateDate = simpleDateFormat.parse(updateDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public long getCommNum() {
        return commNum;
    }

    public void setCommNum(long commNum) {
        this.commNum = commNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
