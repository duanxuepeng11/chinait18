package com.qianfeng.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    //查询所有的用户表里的信息，返回到前台使用AJAX异步请求 --测试 有数据
    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
        System.out.println(userServiceImpl.findAll().size());
        return userServiceImpl.findAll();
    }

    //前台使用AJAX根据手机号 到后台获取姓名，返回到前台
    @RequestMapping("/findByPhone_user")
    @ResponseBody
    public User findByPhone(String phone, HttpServletRequest request){
        System.out.println("findByPhone...."+phone);
        User byPhone = new User();
        if(phone.equals("")){
            //如果为传入值，直接退出
            return byPhone;
        }else{
         byPhone = userServiceImpl.findByPhone(phone);
        String name = byPhone.getName();//得到名字
        //放入到session中
        HttpSession session = request.getSession();
        session.setAttribute("name",name);
        return byPhone;
        }
    }

    @RequestMapping("/togister")
    public String toRes(){
        return "register";
    }

    @RequestMapping("/oruser")
    @ResponseBody
    public int oruser(String name){
        System.out.println("conll");
        int id = userServiceImpl.findUser(name);
        System.out.println("-----: "+ id);
        return id;
    }

    @RequestMapping("/reUser")
    public String reUser(String username,String pass1){
        int i = userServiceImpl.reUser(username,pass1);
        if(i > 0){
            return "login";
        }
        return "registers";
    }

    @RequestMapping("/logins")
    public String logins(String username,String password){
        int id = userServiceImpl.logins(username,password);
        System.out.println("id : " + id);
        if(id > 0){
            return "testsession";
        }else{
            return "login";
        }
    }

}
