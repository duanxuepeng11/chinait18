package com.qianfeng.controller;

import com.qianfeng.domain.Contact;
import com.qianfeng.domain.User;
import com.qianfeng.domain.ZTable;
import com.qianfeng.service.ContactService;
import com.qianfeng.service.DataService;
import com.qianfeng.service.UserService;
import com.qianfeng.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
/**
 * 通话纪录表
 */
public class ContactController {


    @Autowired
    private ContactService contactServiceImpl;

    @Autowired
    private DataService dataServiceImpl;


    @Autowired
    private UserService userServiceImpl;
    //传入 手机号，年，月
    //根据输入的信息进行后台查询
    @RequestMapping("/findByInput")
    @ResponseBody
   public List<ZTable> findThree(String phone, String year, String month, HttpServletRequest request){

       //封装返回到前台的数据
       List<ZTable> ztbles = new ArrayList<ZTable>();
       //封装没个月返回的数据
    //   List<Contact> list = new ArrayList<Contact>();
       System.out.println("month"+month+"1"); //如果没有输入month 得到的值是null
        //根据手机号拿uid
        User user = userServiceImpl.findByPhone(phone);
//       System.out.println(.);
       if(month == null){
           //如果month为空，说明查的整年，不需要关心天，遍历这一年当中的12个月
           System.out.println("你输入的月份为空");

           //遍历12个月的数据
           for(int i=1;i<=12;i++){
               //先去Data表中拿取did
               int did = dataServiceImpl.getDid(year,i);
               if(did >0){

                   //如果did大于0 说明有数据，反之不执行
                   //根据年 跟did 查询contact的数据
                   System.out.println("查询到数据了");
                   System.out.println("uid=>"+user.getUid()+"did=>"+did);
                   Contact contactZ = new Contact(); //创建对象，存放主打人
                   //不能直接拿phone传值。要把phone的的uid值拿出来
                   contactZ= contactServiceImpl.findZhu(user.getUid(),did);
                   System.out.println("主叫人： "+contactZ);

                   Contact contactB = new Contact(); //创建对象，存放主打人
                   contactB= contactServiceImpl.findBei(user.getUid(),did);
                   System.out.println("主叫人： "+contactB);
                   ZTable zt = new ZTable();
                   if(contactZ != null && contactB !=null){
                       zt.setZhudaNum(contactZ.getCallsum());
                       zt.setBeidaNum(contactB.getCallsum());
                       zt.setCount(contactB.getCallcount()+contactZ.getCallcount());
                   }
                   if(contactB !=null && contactZ ==null){
                       zt.setBeidaNum(contactB.getCallsum());
                       zt.setCount(contactB.getCallcount());
                   }
                   if(contactB ==null && contactZ!=null){
                       zt.setZhudaNum(contactZ.getCallsum());
                       zt.setCount(contactZ.getCallcount());
                   }
                   if(contactB ==null && contactZ==null){
                   }
                   ztbles.add(zt);
               }else{
                    //否则不执行
                   System.out.println("没有查询到数据");
                   //返回一个空对象
                   ZTable zt = new ZTable();
                   //如果没有查询到该月的，将本月的数据全部置为0
                   zt.setZhudaNum(0);
                   zt.setBeidaNum(0);
                   zt.setCount(0);
                   ztbles.add(zt);
               }

           }
       }else {
           System.out.println("你输入了全部的条件,则根据月份求出对应的天数");
          // li = contactServiceImpl.findThree(phone,year,month);
           int mon = Integer.parseInt(month);
           //根据月份返回多少天
           int days = 0;
           days = DataUtils.getDays(mon);
           //根据月当中的天去查询数据库
           //循环天数。查数据，设置数据
       }
        HttpSession session = request.getSession();
        session.setAttribute("name",user.getName());

        return ztbles;
   }


}
