package com.qianfeng.APP;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableWebMvc
//@SpringBootApplication
//@ComponentScan(basePackages = { "com.qianfeng.dao"})
@MapperScan("com.qianfeng.mapper")
@SpringBootApplication(scanBasePackages = {"com.qianfeng.controller","com.qianfeng.service"})
public class SpringApplication {
//    com.qianfeng.APP.SpringApplication
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class,args);
    }
}
