package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.ErrorService;
import com.qianfeng.domain.ErrorXZ;
import org.apache.hadoop.hbase.SplitLogTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ErrorXZController {


    @Autowired
    ErrorService errorService;

    /*@RequestMapping("/toerroezx")
    public String toErrorZX(){
        System.out.println("=============");
        return "errrrrr";
    }

    @RequestMapping("/")
    @ResponseBody
    public List<ErrorXZ> getData(){
        System.out.println("====================================");

        List<ErrorXZ> list = new ArrayList<ErrorXZ>();

        return list;
    }*/

}
