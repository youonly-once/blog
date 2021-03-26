package cn.shu.blog.enums;

public enum FileTypeEnum {
    NUMM(0,"NULL"),
    PDF(1,".pdf"),
    HTML(2,".html");

    private final short code;
    private final String type;

    FileTypeEnum(int i, String s) {
        this.code = (short) i;
        this.type = s;
    }
}
