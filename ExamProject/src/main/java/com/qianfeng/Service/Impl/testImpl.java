package com.qianfeng.Service.Impl;

import com.qianfeng.Dao.testDao;
import com.qianfeng.Service.Inter.testInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testImpl implements testInter {
    //使用注入的方式，把dao层的对象 放在service调用 t
    @Autowired
    private testDao testdao;
    //模拟调用方法

    public List findAll(){
        List zhangsan = testdao.findAll("zhangsan", 24);
        return zhangsan;
    }
}
