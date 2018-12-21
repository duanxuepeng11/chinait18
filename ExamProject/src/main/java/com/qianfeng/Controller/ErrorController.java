package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.ErrorService;
import com.qianfeng.domain.ErrorLV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ErrorController {

    @Autowired
    ErrorService errorService;

    @RequestMapping("/tocuowu")
    public String toCuowu(){
        return "cuowu";
    }

    @RequestMapping("/errordd")
    @ResponseBody
    public List<ErrorLV> errorXQ(){
        List<ErrorLV> errorLVS = errorService.errorXQ();
        return errorLVS;
    }

}
