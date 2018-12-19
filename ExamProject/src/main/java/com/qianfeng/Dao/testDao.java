package com.qianfeng.Dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface testDao {

    //模拟 查询数据库所有数据，格式要这样写
    @Select("select * from xxx where name=#{a} and age = #{age}")

    public List findAll(@Param("name") String name, @Param("age") int age);
}
