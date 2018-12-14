package com.qianfeng.mapper;

import com.qianfeng.domain.User;
import org.apache.ibatis.annotations.Insert;
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

    @Select("select id from admin where admin = #{admin}")
    int findUser(@Param("admin") String name);

    @Insert("insert into admin (admin,password) values(#{admin},#{password})")
    int reUser(@Param("admin")String username,@Param("password")String pass);

    @Select("select id from admin where admin = #{admin} and password = #{password}")
    int logins(@Param("admin")String username,@Param("password")String password);
}
