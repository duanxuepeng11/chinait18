package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.testInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private testInter testImpl;

    //访问路径  http://localhost:8091/hadoop/test
    @RequestMapping("/findAll")
    public List findAll(){
        List list = testImpl.findAll();
        //模拟返回list的集合 由于没有数据，我下面另写一个方法返回test字符串
        //然后跳到页面
        return list;
    }
    @RequestMapping("/test")
//    @ResponseBody  这个注解是让返回的字符串 就是一个json类型，不会跳转到页面上
    public String test(){

        return "T";
    }
}
