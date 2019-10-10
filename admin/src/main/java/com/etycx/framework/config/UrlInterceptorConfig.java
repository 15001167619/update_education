package com.etycx.framework.config;

import com.etycx.common.config.Global;
import com.etycx.rest.auth.interceptor.UrlInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author  武海升
 */
@Configuration
public class UrlInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new UrlInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/auth","/auth/","/**/auth/**").excludePathPatterns("/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //添加图片访问
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:"+ Global.getProfile()+"image/");
        //添加资源访问
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+ Global.getProfile()+"upload/");


    }
}
