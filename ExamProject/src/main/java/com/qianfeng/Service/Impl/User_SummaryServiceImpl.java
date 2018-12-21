package com.qianfeng.Service.Impl;

import com.qianfeng.Dao.User_SummaryDao;
import com.qianfeng.Service.Inter.User_SummaryServiceInter;
import com.qianfeng.domain.Ability;
import com.qianfeng.domain.Error_Rate;
import com.qianfeng.domain.ExamineeInfo;
import com.qianfeng.domain.User_Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_SummaryServiceImpl implements User_SummaryServiceInter {
    @Autowired
    private User_SummaryDao user_summaryDao;

    // 根据用户名来查找学生id
    @Override
    public List<ExamineeInfo> findIdByName() {
        List<ExamineeInfo> info =  user_summaryDao.findIdByName();
        return info;

    }
    @Override
    public List<User_Summary> findAll(String examinee_num) {
        List<User_Summary> list = user_summaryDao.findAll(examinee_num);
        return list;
    }

    @Override
    public Ability findAbilityByNum(String examinee_name) {
        return user_summaryDao.findAbilityByNum(examinee_name);
    }

    @Override
    public List<Error_Rate> findErrorByCategory(String category_name) {
        return user_summaryDao.findErrorByCategory(category_name);
    }
}
