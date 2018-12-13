package com.qianfeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Admin {

    //admin登陆 接口哦

    @RequestMapping("/logins")
    public String login(String username,String password){
        if(username.equals("admin") && password.equals("admin")){
            System.out.println("登陆成功");
            return "testsession";
        }else {
            return "login";
        }
    }
}
