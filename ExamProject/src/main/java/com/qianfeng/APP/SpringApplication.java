package com.qianfeng.APP;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableWebMvc
//@SpringBootApplication
//@ComponentScan(basePackages = { "com.qianfeng.dao"})
@MapperScan("com.qianfeng.Dao")
@SpringBootApplication(scanBasePackages = {"com.qianfeng.Controller","com.qianfeng.Service"})
public class SpringApplication {
//    com.qianfeng.APP.SpringApplication
    public static void main(String[] args) {
        //3.11加入了一个注释
        org.springframework.boot.SpringApplication.run(SpringApplication.class,args);
    }
}
