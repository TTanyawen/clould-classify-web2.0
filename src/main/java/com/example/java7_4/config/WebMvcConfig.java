//package com.example.java7_4.config;
//
//import com.example.java7_4.interceptor.JwtInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("addInterceptors");
//        registry.addInterceptor(new JwtInterceptor())
//                .addPathPatterns("/**") // 需要拦截的路径
//                .excludePathPatterns("/index/toLogin", "/index/doLogin","/index","/","/know",
//                        "/checkRegister",
//                        "/js/**",        // 排除静态资源路径
//                        "/css/**",
//                        "/images/**",
//                        "/favicon.ico",
//                        "/error"); // 放行的路径
//    }
//}

package com.example.java7_4.config;

import com.example.java7_4.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import io.swagger.annotations.Api;


@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private final JwtInterceptor jwtInterceptor;

    @Autowired
    public WebMvcConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors");
        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/**") // 需要拦截的路径
                .addPathPatterns("/user/**") // 需要拦截的路径
                .addPathPatterns("/post/**") // 需要拦截的路径
                .excludePathPatterns("/user/checkRegister")
                .excludePathPatterns("/user/doLogin")
//                .excludePathPatterns("/user/getForumData")
                .excludePathPatterns("/user/getMeData")
                .excludePathPatterns("/user/getDetailData")
                .excludePathPatterns("/user/logOut")
//                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns(
                        "/js/**",        // 排除静态资源路径
                        "/css/**",
                        "/images/**",
                        "/images/profiles/**",
                        "/favicon.ico",
                        "/error",
                        "/test"); // 放行的路径
    }




    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("开始静态资源映射");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 添加静态资源映射
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:./static/images/");
        // 映射 css 文件
        registry.addResourceHandler("/css/**")
                .addResourceLocations("file:./static/css/");
    }


}

