package com.ohgiraffers.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration              // 이미 구현되어있는 WebMvcConfigurer 상속 받아줘야함
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    // 인터셉터 목록을 받아와서 추가하는
    public void addInterceptors(InterceptorRegistry registry) {

        // 인자로 전달받아서
        registry.addInterceptor(new StopWatchInterceptor(new MenuService()))
                .addPathPatterns("/*")
                /* static 하위의 정적 리소스는 인터셉터가 적용되지 않도록 한다. */
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                /* /error로 포워딩 되는 경로도 제외해주어야 한다. */
                .excludePathPatterns("/error");
    }
}
