package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.VipStudentService;
import com.qianfeng.domain.VIPStudent;
import com.qianfeng.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VipController {

    @Autowired
    VipStudentService vipStudentService;

    @RequestMapping("/vipdd")
    @ResponseBody
    public PageBean<VIPStudent> vipStudent(String select_value, Integer code, Integer size) {
        System.out.println(select_value + " " + code + " " + size);
        PageBean<VIPStudent> page = vipStudentService.findAll(code, size, select_value);
        return page;
    }

    @RequestMapping("/tovip")
    public String toVip() {
        System.out.println("vip...");
        return "vip";
    }

}
