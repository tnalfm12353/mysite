package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.douzone.mysite.controller", "com.douzone.mysite.exception"})
@Import({MvcConfig.class, MessageConfig.class, FileUploadConfig.class, SecurityConfig.class})
public class WebConfig {

}

