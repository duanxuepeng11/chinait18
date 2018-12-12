package com.qianfeng.controller;

import com.qianfeng.domain.Happiness;
import com.qianfeng.service.HappinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HappinessController {
    @Autowired
    private HappinessService happinessService;

    @RequestMapping("/query")
    public String testQuery(){
        Happiness ss = happinessService.selectService("北京");
        System.out.println("222222222222");
        return "test";
    }

    @RequestMapping("/index")
    public String testInsert(){
        //happinessService.insertService();
        System.out.println("2222222222222");
        return "echart";
    }

    @RequestMapping("/test1")
    //@ResponseBody
    public String test(){
        Happiness ss = happinessService.selectService("北京");
        System.out.println("33333333333333");
        return "test";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Happiness> findAll(){
        System.out.println("findAll....");
        List<Happiness> all = happinessService.findAll();
        return all;
    }

//    public static void main(String[] args) {
//        new HappinessController().
//    }
    //测试表单能否提交数据过来   测试通过，可以获取到phone
    // 表单里面的信息，跟方法里面的参数一样就行了
    @RequestMapping("/findphone")
    public String from_biaodan(String phone,String year){
        System.out.println("phone==>"+phone);
        //如果后台没有传入phone  则是 ""  空
         System.out.println("year==>"+year);
         System.out.println("year is false？"+year.equals(""));
        return "echart";
    }
}

