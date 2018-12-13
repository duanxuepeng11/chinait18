package com.qianfeng.mapper;

import com.qianfeng.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    @Select("select * from user")
    List<User> findAll();

    //根据手机号查询该uid
    @Select("select * from user where phone =#{phone}")
    User findByPhone(@Param("phone") String phone);

    //根据用户的uid查询
    @Select("select * from user where uid=#{uid_you}")
    User findByUid(@Param("uid_you") int uid_you);
}
