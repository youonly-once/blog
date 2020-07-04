package cn.shu.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @作者 舒新胜
 * @项目 MyBlog
 * @创建时间 2020-5-22 18:05
 */
@Configuration
public class AutoInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AutoInterceptor());
        //拦截所有路径
        interceptorRegistration.addPathPatterns("/**");
       // interceptorRegistration.excludePathPatterns();
    }
}
