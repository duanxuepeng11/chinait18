package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.User_SummaryServiceInter;
import com.qianfeng.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class User_SummaryContoller {

    // 注入的形式，调用service曾的对象
    @Autowired
    private User_SummaryServiceInter user_summaryImpl;

    // 跳转到首页
    @RequestMapping("/findSummary")
    public String findSummary() {
        return "Summary";
    }

    // 根据考试id和阶段来查询学生信息
    @RequestMapping("/user_summary")
    @ResponseBody
    public List<User_Summary> findByExaminee_num(String examinee_name) {
        List<ExamineeInfo> infoList = user_summaryImpl.findIdByName();
        HashMap<String, String> infoMap = new HashMap<String, String>();
        for (ExamineeInfo info : infoList) {
            String name = info.getExaminee_name();
            String num = info.getExaminee_num();
            infoMap.put(name, num);
        }
        String examinee_num = infoMap.get(examinee_name);
        List<User_Summary> list = user_summaryImpl.findAll(examinee_num);
        List<User_Summary> resList = new ArrayList<User_Summary>();
        int i = 0;
        for (User_Summary summary : list) {
            User_Summary sm = new User_Summary();
            String examinee_num1 = summary.getExaminee_num();
            String name = examinee_name;
            sm.setExaminee_name(name);
            sm.setCategory_name(summary.getCategory_name());
            sm.setClass_name(summary.getClass_name());
            sm.setExam_id(summary.getExam_id());
            sm.setExaminee_num(examinee_num1);
            sm.setQuestion_difficulty(summary.getQuestion_difficulty());
            sm.setScore_avg(summary.getScore_avg());
            sm.setStart_time(summary.getStart_time());
            resList.add(sm);
            i++;
        }
        return resList;
    }

    // 根据学号查询各种能力
    @RequestMapping("/findAbility")
    public String findAbility() {
        return "Ability";
    }

    // 根据考试id和阶段来查询学生信息
    @RequestMapping("/user_Ability")
    @ResponseBody
    public Ability findAbility(String examinee_name) {
        Ability ability = user_summaryImpl.findAbilityByNum(examinee_name);
        return ability;
    }

    // 跳转到错误率页面
    @RequestMapping("/findPaperErr")
    public String findPaperError() {
        return "PaperErr";
    }

    // 根据考试场次名查找每题错误率
    @RequestMapping("/findErrorByCategory")
    @ResponseBody
    public List<Error_Rate> findErrorByCategory(String category_name) {
        List<Error_Rate> list = user_summaryImpl.findErrorByCategory(category_name);
        return list;
    }
    // 根据学号和考场的id进行查找学生的信息
    @RequestMapping("/findHbaseInfoById")
    public String findInfoById(){return "T";}
    @RequestMapping("/findHbaseInfo")
    @ResponseBody
    public List<Xiangqing> findHbaseInfo(String examinee_num,String exam_id) throws Exception {
        String startRow = examinee_num+","+exam_id+","+1;
        String endRow = examinee_num+","+exam_id+","+9999;
        System.out.println(startRow+":"+endRow);
        List<Xiangqing> list = user_summaryImpl.findAllByExaminee_Num2Hbase(startRow, endRow);
        return list;
    }
    // 根据学号和考场的id进行查找学生的信息
    @RequestMapping("/findHbaseInfoById_")
    public String findInfoById_(){return "T1";}
    @RequestMapping("/findHbaseInfo_")
    @ResponseBody
    public List<Huizong> findHbaseInfo_(String class_num,String exam_id) throws Exception {
        String startRow = class_num+","+exam_id+","+1;
        String endRow = class_num+","+exam_id+","+9999;
        System.out.println(startRow+":"+endRow);
        List<Huizong> list = user_summaryImpl.findAllByClass_name2Hbase(startRow, endRow);
        return list;
    }
}
