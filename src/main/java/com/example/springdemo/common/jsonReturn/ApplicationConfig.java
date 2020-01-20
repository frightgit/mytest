package com.example.springdemo.common.jsonReturn;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @des: 配置bean
 * @Date: 2019/11/28 13:50
 * @Auther: wjc
 */
@Configuration
@ComponentScan(basePackages = {"com.example.springdemo"},useDefaultFilters = true)
@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter{
    @Bean
    public JsonReturnHandler jsonReturnHandler(){
        return new JsonReturnHandler();//初始化json过滤器
    }
    @Override
    public void addReturnValueHandlers(
            List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(jsonReturnHandler());
    }
}
