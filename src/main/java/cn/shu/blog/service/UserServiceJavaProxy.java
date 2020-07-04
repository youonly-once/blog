package cn.shu.blog.service;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/4/29
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;

/**
 * JAVA自带的动态代理实现方式
 * 该类为工厂类，创建出实际的代理者 交给Spring容器管理
 */
//@Service//由Web层调用
public class UserServiceJavaProxy {
    /*
     * 被代理者
     */
    @Autowired //自动注入
    @Qualifier("userService") //注入ID为 userService的类
    private UserServiceInter userServiceInter=null;

    /**
     * 创建代理者
     */
    @Bean("userServiceInter")
    public UserServiceInter getProxy(){
        /**

         * classLoader:用来生成代理者类的类加载器，通常可以传入被代理者类的类加载器

         * interfaces: 要求生成的代理者实现的接口们，通常就是实现和被代理者相同的接口，保证具有和被代理者相同的方法

         * invocationHandler: 用来设定回调函数的回调接口，使用者需要写一个类实现此接口，从而实现其中的invoke方法，

         * 在其中编写代码处理代理者调用方法时的回调过程，通常在这里调用真正对象身上的方法，并且在方法之前或之后做额外操作。

         */

        UserServiceInter userServiceProxy = (UserServiceInter)Proxy.newProxyInstance(userServiceInter.getClass().getClassLoader()
                , userServiceInter.getClass().getInterfaces()
                , (proxy, method, args) -> {

                    //额外操作
                    System.out.println("JAVA 动态代理者 日志记录:" + method.getName());

                    //反射调用被代理者真正的方法
                    Object returnObj = method.invoke(userServiceInter, args);
                    return returnObj;
                });
        return userServiceProxy;
    }
}
