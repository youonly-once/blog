package cn.shu.blog.aspect;

import cn.shu.blog.annotations.Transaction;
import cn.shu.blog.utils.database.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-6 18:08
 */
/*@Component
@Aspect*/
//暂不用 用Spring的事务管理
public class TransactionManagerAspect {

  /*  @Around("execution(* cn.shu.blog.service..*(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        //1、获取目标对象
        Object target = pjp.getTarget();

       //2、获取当前调用的方法 这个方法是接口上的 可能没有注解 注解一般用在实现类上
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();

        //3、获取目标对象的方法 和接口上方法名相同 参数名相同的方法
        Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());

        //4、判断是否有事务控制的注解 有些方法无需事务控制
        boolean isTransaction = targetMethod.isAnnotationPresent(Transaction.class);

        if (isTransaction){ //有事务控制
            //当前线程对应的Connection开启事务
            TransactionManager.startTrans();
            Object proceed=null;
            try {
                proceed = pjp.proceed();
                System.out.println(proceed);
                //提交事务
                TransactionManager.commitTrans();
            } catch (Throwable throwable) {
                //回滚事务
                TransactionManager.rollbackTrans();
                throwable.printStackTrace();
                throw throwable;
            }finally {
                TransactionManager.close();
            }
            return proceed;
        }
        //无事务控制
        return pjp.proceed();

    }*/

    //改进:利用spring提供的切入点表达式来直接获取注解
    //此表达式&& @annotation(ano) 表示 切出包括 注解为Transaction的的方法
    @Around("execution(* cn.shu.blog.service..*(..)) && @annotation(ano)")
    public Object myAround(ProceedingJoinPoint pjp,Transaction ano) throws Throwable {

            //当前线程对应的Connection开启事务
           // TransactionManager.startTrans();
            Object proceed=null;
            try {
                proceed = pjp.proceed();
                //提交事务
              //  TransactionManager.commitTrans();
            } catch (Throwable throwable) {
                //回滚事务
               // TransactionManager.rollbackTrans();
                throwable.printStackTrace();
                throw throwable;
            }finally {
                //关闭conn连接 只能在这里关闭
                TransactionManager.close();
            }
            return proceed;

    }
}
