package com.qianfeng.Dao;

import com.qianfeng.domain.qtxtableone;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface qtxtableoneDao {

    @Select("select * from g_qtxtableone where examinee_num=#{examinee_num} group by question_name;")
    List<qtxtableone> selectAll(@Param("examinee_num") String examinee_num);


}
