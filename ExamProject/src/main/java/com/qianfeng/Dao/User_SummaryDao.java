package com.qianfeng.Dao;

import com.qianfeng.domain.Ability;
import com.qianfeng.domain.Error_Rate;
import com.qianfeng.domain.ExamineeInfo;
import com.qianfeng.domain.User_Summary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface User_SummaryDao {
    // 根据姓名查找学号 a
    @Select("select examinee_num,examinee_name from basic_huizong")
    List<ExamineeInfo> findIdByName();

    // 根据考试id和考试 阶段来查询学生的考试信息。
    @Select("select * from user_summary where examinee_num =#{names}")
    List<User_Summary> findAll(@Param("names") String examinee_num);

    @Select("select examinee_num,examinee_name,lijie,biaoda,biancheng from user_model where examinee_name =#{names}")
    Ability findAbilityByNum(@Param("names") String examinee_name);

    @Select("select question_id,category_name,error_rate from test_error_rate where category_name=#{names}")
    List<Error_Rate> findErrorByCategory(@Param("names") String category_name);
}
