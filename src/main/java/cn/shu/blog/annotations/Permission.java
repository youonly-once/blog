package cn.shu.blog.annotations;

import cn.shu.blog.enums.PermissionEnum;

import java.lang.annotation.*;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 14:12
 * 自定义注解 说明某个方法的操作需要什么权限
 */

@Target(ElementType.METHOD)//声明该注解 只能用再方法上。
@Retention(RetentionPolicy.RUNTIME)//声明该自定义注解 加载到内存中也可用。
@Documented//用来声明被修饰注解是否要被文档提取工具提取到文档中(默认不提取)。
@Inherited //被修饰的注解是否具有继承性(默认没有继承性)。

public @interface Permission {
    /**
     * 为注解类声明属性的过程非常类似于为接口定义方法。
     *
     * 注解中的所有的属性必须是public的，可显式声明，也可不声明默认是public。
     *
     * 注解中的属性只能是八种基本数据类型、 String类型 、Class类型 、枚举类型 、其他注解类型 及 以上类型的一维数组。
     *
     * 注解中声明的属性 需要在使用注解时 为其赋值 赋值的方式 就是使用注解时 在注解后跟一对小括号 在其中 通过 属性名=属性值 的方式 指定属性的值
     * 例：@Permission(key="value")
     * 也可以在声明注解时 在注解的属性后 通过default关键字 声明属性的默认值
     * 声明过默认值的属性 可以在使用注解时 不赋值 则默认采用默认值 也可以手动赋值 覆盖默认值
     *
     * 如果属性是 一维数组类型 而 在传入的数组中 只有一个值 则包括数组的大括号可以省略
     *
     * 如果注解的属性 只有一个需要赋值 且该属性的名称叫做value 则在使用注解时 value= 可以不写
     * 例：@Permission("值")
     * @return
     */
    public PermissionEnum value() default PermissionEnum.USER;
}
