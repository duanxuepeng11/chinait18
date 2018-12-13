package com.qianfeng.mapper;

import com.qianfeng.domain.Contact;
import com.qianfeng.domain.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContactDao {

    List<Contact> findThree(String phone,String year,String month);

    //根据年跟did查询手机号主打数据量
    @Select("select * from contact where uid=#{uid} and did=#{did} and statue=1 ")
    List<Contact> findZhu(@Param("uid") int uid, @Param("did") int did);

    //根据年跟did查询手机号被打数据量

    @Select("select * from contact where uid=#{uid} and did=#{did} and statue=0 ")
    List<Contact> findBei(@Param("uid") int uid,@Param("did") int did);

//    @Select("select * from contact where ")
    Data findZhu2(int uid, int did);
}
