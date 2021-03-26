package cn.shu.blog.enums;

public enum Test {
    b((short)0),
    a((short)56);

    private short VALUE;
    Test(short value){
    this.VALUE = value;
    }
}
