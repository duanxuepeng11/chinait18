package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.User_SummaryServiceInter;
import com.qianfeng.domain.User_Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class User_SummaryContoller {
    // 注入的形式，调用service曾的对象
    @Autowired
    private User_SummaryServiceInter user_summaryImpl;
    // 根据学生学生姓名进行查找
    @RequestMapping("/user_summary")
    public List<User_Summary> findByExaminee_num(){
return null;
    }


}
