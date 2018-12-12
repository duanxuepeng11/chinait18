package com.qianfeng.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface DataDao {

    //根据年 月取出来did
    @Select("select ifnull(did,0) from datas where year=#{year} and month=#{id}")
    int getDid(@Param("year") String year, @Param("id") int i);
}
