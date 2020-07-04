package cn.shu.blog.aspect;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 11:13
 */

import cn.shu.blog.beans.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 权限控制 暂未实现 现在网站没有要实现权限控制的内容
 */
@Component
@Aspect
public class PermissionAspect {
    @Around("execution(* cn.shu.blog.service..*(..)) ")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        User user=null;
   /*     for (Object arg:
             args) {
            if (arg.getClass()== User.class){
                user=(User)arg;
                break;
            }
        }*/

      /*  System.out.println("Permission");
        throw new RuntimeException("权限不足");*/
        return pjp.proceed();
    }
}
