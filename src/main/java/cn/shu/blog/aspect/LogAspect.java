package cn.shu.blog.aspect;



import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 配置切面
 * 记录 Dao层及 Service层日志
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/4/30
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution( * cn.shu.blog..*(..))")
    public void pointCut(){

    }

    /*
    * 定义前置通知
    * */
    //@Before("execution( * cn.shu.blog.service..*(..))")
    @Before("pointCut()")
    public void myBefore(JoinPoint joinPoint){
        HashMap<String, Object> targetInformation = AspectUtil.getTargetInformation(joinPoint);
        //传递的参数
        String args = Arrays.toString(joinPoint.getArgs());
        log.debug("调用方法:["+ targetInformation.get("targetMethod")+"]，参数:"+ args);
    }



    /**
     * 定义后置通知
     * @param joinPoint
     *
     */
   // @AfterReturning("execution(* cn.shu.blog.service.*.*(..))")
    @AfterReturning(value = "pointCut()",returning = "returnObj")
    public void myAfterReturning(JoinPoint joinPoint,Object returnObj){

        HashMap<String, Object> targetInformation = AspectUtil. getTargetInformation(joinPoint);
        log.debug("调用方法:["+ targetInformation.get("targetMethod")+"]，返回值:["+returnObj+"]");
    }

    /**
     * 定义异常后置通知
     * @param joinPoint
     * @param e 抛出的异常
     */
  //  @AfterThrowing("execution(* cn.shu.blog.service.*.*(..))")
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint,Throwable e){
        HashMap<String, Object> targetInformation = AspectUtil.getTargetInformation(joinPoint);
        log.debug("调用方法:["+ targetInformation.get("targetMethod")+"]，出现异常:["+e+"]");
    }

    /**
     * 定义最终通知
     * @param joinPoint
     */
   // @After("execution(* cn.shu.blog.service.*.*(..))")
    @After(value = "pointCut()")
    public void myAfter(JoinPoint joinPoint){

    }
}
