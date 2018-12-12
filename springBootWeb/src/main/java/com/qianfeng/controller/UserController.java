package com.qianfeng.controller;

import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User byPhone = userServiceImpl.findByPhone(phone);
        String name = byPhone.getName();//得到名字
        //放入到session中
        HttpSession session = request.getSession();
        session.setAttribute("name",name);
        return byPhone;
    }

}
