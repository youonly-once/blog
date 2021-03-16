package cn.shu.blog.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Method;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020/4/29
 */
//@Service
@Slf4j
public class UserServiceCglibProxy {
    @Autowired
    @Qualifier("userService")
    private UserServiceInter userServiceInter=null;

    @Bean("userServiceInter")
    @Scope("singleton")
    public UserServiceInter getProxy(){

        //增强器
        Enhancer enhancer=new Enhancer();

        //设定接口 -- 此方法要求生成的动态代理额外实现指定接口们 ，单cglib动态代理不是靠接口实现的，所以可以不设置
        enhancer.setInterfaces(userServiceInter.getClass().getInterfaces());

        //设定父类 -- 此处要传入被代理者的类，cglib是通过集成被代理者的类来持有和被代理者相同的方法的，此方法必须设置
        enhancer.setSuperclass(userServiceInter.getClass());

        //设定回调函数 -- 为增强器设定回调函数，之后通过增强器生成的代理对象调用任何方法都会走到此回调函数中，实现调用真正被代理对象的方法的效果
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //额外操作
                 log.info("Cglib 动态代理者 日志记录:" + method.getName());

                //反射调用被代理者真正的方法
                Object returnObj = method.invoke(userServiceInter, args);
                return returnObj;
            }
        });
        //生成代理对象
        UserServiceInter userServiceInter = (UserServiceInter)enhancer.create();
        return userServiceInter;
    }
}
