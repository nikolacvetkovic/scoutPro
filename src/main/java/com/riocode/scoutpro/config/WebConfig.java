package com.riocode.scoutpro.config;

import com.riocode.scoutpro.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 *
 * @author Nikola Cvetkovic
 */

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Value("${webdriver.path}")
    private String webDriverPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }

    @PostConstruct
    public void setProperty(){
        System.setProperty("webdriver.chrome.driver", webDriverPath);
    }
    
}
