/*
package com.login.loginAPI.config;

import com.login.loginAPI.interceptor.sessionCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class sessionCheckConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(sessionCheckInterceptor())
                .addPathPatterns("/item/**")
                .addPathPatterns("/memberList/**")
                .addPathPatterns("/profile/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/createAccount/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    @Bean
    public sessionCheckInterceptor sessionCheckInterceptor(){
        return new sessionCheckInterceptor();
    }
}
*/
