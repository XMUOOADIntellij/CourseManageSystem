package com.group12.course.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Web config
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new JwtInterceptor()).excludePathPatterns("/user/login");

    }

}
