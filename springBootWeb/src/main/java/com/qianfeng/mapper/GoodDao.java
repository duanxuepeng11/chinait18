package com.qianfeng.mapper;

import com.qianfeng.domain.Good;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodDao {
    @Select("select gid,uid_my,uid_you,count(love) AS love,did from goods WHERE uid_my = #{uid} and did=#{did} group by uid_you order by love desc limit 5")
    List<Good> list_good(@Param("uid") int uid,@Param("did") int did);
}
