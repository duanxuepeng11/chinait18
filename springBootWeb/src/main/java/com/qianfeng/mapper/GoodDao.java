package com.qianfeng.mapper;

import com.qianfeng.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodDao {
    @Select("select * from goods WHERE phone = #{name}")
    List<Good> list_good(@Param("name") String name);
}
