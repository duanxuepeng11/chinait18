package com.qianfeng.Dao;

import com.qianfeng.domain.ErrorLV;
import com.qianfeng.domain.ErrorXZ;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ErrorMapping {

    @Select("SELECT * from topn_error_rate where error_rate < 100 ORDER BY error_rate DESC LIMIT 0,20")
    public List<ErrorLV> errorXQ();


    @Select("select * from test_correct")
    public List<ErrorXZ> getData();

}
