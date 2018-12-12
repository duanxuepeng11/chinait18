package com.qianfeng.service.Impl;

import com.qianfeng.domain.Good;
import com.qianfeng.mapper.GoodDao;
import com.qianfeng.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodDao goodDao;
    @Override
    public List<Good> list_good(String name) {
        return goodDao.list_good(name);
    }
}
