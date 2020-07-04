package cn.shu.blog.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 获取方法执行时间
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 11:43
 */
@Component
@Aspect
@Slf4j
public class ProcessTimeAspect {
    /**
     * 定义环绕通知
     * 这里用来看方法执行时间
     * @param proceedingJoinPoint
     */

   @Around("execution(* cn.shu.blog..*(..))")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HashMap<String, Object> targetInformation = AspectUtil.getTargetInformation(proceedingJoinPoint);
        long begin=System.currentTimeMillis();
        Object returnObj = proceedingJoinPoint.proceed();
        long end=System.currentTimeMillis();
       log.debug("调用方法:["+ targetInformation.get("targetMethod")+"]，耗时:"+ (end-begin)+"毫秒");
        return returnObj;
    }
}
