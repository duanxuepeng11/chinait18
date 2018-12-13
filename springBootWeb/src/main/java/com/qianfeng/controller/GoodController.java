package com.qianfeng.controller;

import com.qianfeng.domain.Data;
import com.qianfeng.domain.Good;
import com.qianfeng.domain.GoodBean;
import com.qianfeng.domain.User;
import com.qianfeng.service.DataService;
import com.qianfeng.service.GoodService;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class GoodController {


    @Autowired
    private GoodService goodServiceImpl;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private DataService dataServiceImpl;
    //传入一个手机号，可以查看他的电话记录，并测试亲密度
    //未测试
    @RequestMapping("/findByphone_Good")
    @ResponseBody
    public List<GoodBean> list_good(String phone,String year,String month){

        System.out.println("year="+year+"month="+month);
        //根据用户手机去user表中获取uid
        User byPhone = userServiceImpl.findByPhone(phone);
        //根据传的时间，获取did
        Data data = new Data();
        System.out.println("month="+month);
//        if(Integer.parseInt(month) <10 && Integer.parseInt(month) >0){
//            month="0"+month;
//        }
        data =dataServiceImpl.getByYearAndMonth(year,month);
        System.out.println("data"+data);
        //查询出来，根据手机号人的uid  did查询匹配的数据

        System.out.println("uid=>"+byPhone.getUid() +"did="+data.getDid());
        List<Good> goods = goodServiceImpl.list_good(byPhone.getUid(),data.getDid());
        List<GoodBean> goodBeans = new ArrayList<GoodBean>();

        //获取一个好右集合 测试通过
        System.out.println("goods==>"+goods+"长度"+goods.size());
        //封装向外传入的参数集合类型

            Collections.sort(goods);
            for(int i=0;i<goods.size();i++){
                //根据uid返回用户信息 ，主要取出来用户名
                GoodBean gb = new GoodBean();
                User user =userServiceImpl.findByUid(goods.get(i).getUid_you());
                gb.setName(user.getName());//+"-特别熟悉"
                gb.setValue(goods.get(i).getLove());
                goodBeans.add(gb);
            }


        System.out.println(goodBeans);
        return goodBeans;
    }
}
