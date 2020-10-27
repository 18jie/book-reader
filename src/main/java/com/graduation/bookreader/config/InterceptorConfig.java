package com.graduation.bookreader.config;

import com.graduation.bookreader.filter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 * 拦截器配置类
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 22:13
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/register");
    }
}
