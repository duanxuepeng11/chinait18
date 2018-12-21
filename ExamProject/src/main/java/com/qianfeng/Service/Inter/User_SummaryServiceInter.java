package com.qianfeng.Service.Inter;

import com.qianfeng.domain.Ability;
import com.qianfeng.domain.Error_Rate;
import com.qianfeng.domain.ExamineeInfo;
import com.qianfeng.domain.User_Summary;

import java.util.List;

public interface User_SummaryServiceInter {
    List<ExamineeInfo> findIdByName();
    List<User_Summary> findAll(String examinee_name);

    Ability findAbilityByNum(String examinee_name);

    List<Error_Rate> findErrorByCategory(String category_name);
}
