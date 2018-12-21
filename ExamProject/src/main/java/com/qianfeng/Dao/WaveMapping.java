package com.qianfeng.Dao;

import com.qianfeng.domain.WaveStudent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WaveMapping {

    @Select("SELECT * from  bodong where examinee_name = #{name} ORDER BY exam_id")
    public List<WaveStudent> findByNameAll(@Param("name") String examinee_name);

}
