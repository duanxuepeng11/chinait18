package com.qianfeng.Controller;

import com.qianfeng.domain.qtxtableone;
import com.qianfeng.Service.Inter.qtxtableoneInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class qtxtableoneController {

    @Autowired
    private qtxtableoneInter qtxtableoneImpl;

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<qtxtableone> selectAll(String examinee_num){
        //调用service下的方法,用于与数据库的交互
        List<qtxtableone> all = qtxtableoneImpl.selectAll(examinee_num);
        return all;
    }

    @RequestMapping("/qtx")//该注解是与前台实现通道的接口
    public String gao(){
        return "qtxtableone";
    }
}
