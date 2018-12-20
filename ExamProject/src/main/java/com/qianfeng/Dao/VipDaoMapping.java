package com.qianfeng.Dao;

import com.qianfeng.domain.VIPStudent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VipDaoMapping {

    @Select("select * from vipstudent limit #{code},#{size}")
    public List<VIPStudent> findAllVip(@Param("code") Integer pageCode,@Param("size") Integer pageSizes);

    @Select("select * from pettyofficial limit #{code},#{size}")
    public List<VIPStudent> findAllPettyOfficial(@Param("code") Integer pageCode,@Param("size") Integer pageSizes);

}
