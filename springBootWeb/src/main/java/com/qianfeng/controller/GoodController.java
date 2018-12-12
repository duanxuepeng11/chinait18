package com.qianfeng.controller;

import com.qianfeng.domain.Good;
import com.qianfeng.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GoodController {


    @Autowired
    private GoodService goodServiceImpl;

    //传入一个手机号，可以查看他的电话记录，并测试亲密度
    //未测试
    public List<Good> list_good(){
        String name = "18866559655";
        List<Good> goods = goodServiceImpl.list_good(name);
        System.out.println("goods==>"+goods);
        return goods;
    }
}
