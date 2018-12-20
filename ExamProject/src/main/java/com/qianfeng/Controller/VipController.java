package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.VipStudentService;
import com.qianfeng.domain.VIPStudent;
import com.qianfeng.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vip")
public class VipController {

    @Autowired
    VipStudentService vipStudentService;

    // 当前页面，默认为 1
    private Integer pageCode = 1;
    public void setPageCode(Integer pageCode) {
        if(pageCode == null){
            pageCode = 1;
        }
        this.pageCode = pageCode;
    }
    // 每页显示数据的条数
    private Integer pageSize = 10;
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @RequestMapping("/vip")
    public List<VIPStudent> vipStudent(String select_value){
        PageBean<VIPStudent> page = vipStudentService.findAll(pageCode,pageSize,select_value);
        List<VIPStudent> beanList = page.getBeanList();
        return beanList;
    }




}
