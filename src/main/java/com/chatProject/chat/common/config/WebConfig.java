package com.chatProject.chat.common.config;

import com.chatProject.chat.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) // 인터셉터 등록
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/user/**", "/js/**", "/css/**", "/favicon.ico")
                .excludePathPatterns("/api/**/**/**");
    }
}
