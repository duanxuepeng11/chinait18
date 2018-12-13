package com.qianfeng.mapper;

import com.qianfeng.domain.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface DataDao {

    //根据年 月取出来did
    @Select("select ifnull(did,0) from datas where year=#{year} and month=#{id} and day =-1")
    int getDid(@Param("year") String year, @Param("id") int i);

    @Select("select * from datas where year=#{year} and month=#{month} and day =-1")
    Data getByYearAndMonth(@Param("year") String year,@Param("month") String month);

    @Select("select ifnull(did,0) from datas where year=#{year} and month=-1 and day =-1")
    Data getByYear(@Param("year") String year);

    @Select("select ifnull(did,0) from datas where year=#{year} and month=#{month} and day =#{i}")
    int getByThree(@Param("year") String year,@Param("month")  String month,@Param("i") int i);
}
