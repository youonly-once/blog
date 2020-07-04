package cn.shu.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 11:45
 */
public class AspectUtil {
    public static HashMap<String,Object> getTargetInformation(JoinPoint joinPoint){
        HashMap<String,Object> info=new HashMap<>();
        //目标类
        Object target = joinPoint.getTarget();
        Class<?> targetClass = target.getClass();
        //目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = signature.getMethod();
        info.put("targetClass",targetClass);
        info.put("targetMethod",targetMethod);
        return info;
    }
}
