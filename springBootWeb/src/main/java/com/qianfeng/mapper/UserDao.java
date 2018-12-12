package com.qianfeng.mapper;

import com.qianfeng.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> findAll();

    //根据手机号查询该uid
    @Select("select * from user where phone =#{phone}")
    User findByPhone(@Param("phone") String phone);
}
