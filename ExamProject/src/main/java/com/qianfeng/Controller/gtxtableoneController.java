package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.gtxtableoneInter;
import com.qianfeng.domain.gtxtableone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class gtxtableoneController {

    @Autowired
    private gtxtableoneInter gtxtableoneImpl;

    @RequestMapping("/gtxselectAll")
    @ResponseBody
    public List<gtxtableone> selectAll(String examinee_num){
        //调用service下的方法,用于与数据库的交互
        List<gtxtableone> all = gtxtableoneImpl.selectAll(examinee_num);
        return all;
    }

    @RequestMapping("/gtx")//该注解是与前台实现通道的接口
    public String gao(){
        return "gtxtableone";
    }
}
