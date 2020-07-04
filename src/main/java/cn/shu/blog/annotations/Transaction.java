package cn.shu.blog.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 18:05
 * 标注事务管理的注解
 */
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target(ElementType.METHOD)//只用于方法上
public @interface Transaction {
}
