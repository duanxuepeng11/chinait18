package com.qianfeng.Dao;

import com.qianfeng.domain.gtxtableone;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface gtxtableoneDao {

    @Select("select * from g_danxone where examinee_num=#{examinee_num} group by question_name;")
    List<gtxtableone> selectAll(@Param("examinee_num") String examinee_num);


}
