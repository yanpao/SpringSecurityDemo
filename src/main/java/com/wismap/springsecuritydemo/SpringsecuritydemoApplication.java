package com.wismap.springsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.wismap.springsecuritydemo.mapper")
@ComponentScan(basePackages ={"com.wismap.springsecuritydemo.utils",
        "com.wismap.springsecuritydemo.security",
        "com.wismap.springsecuritydemo.model",
        "com.wismap.springsecuritydemo.controller",
        "com.wismap.springsecuritydemo.config",
        "com.wismap.springsecuritydemo.service",
        "com.wismap.springsecuritydemo.mapper"} )
public class SpringsecuritydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritydemoApplication.class, args);
    }

}
