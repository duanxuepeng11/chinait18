package com.qianfeng.service;

import com.qianfeng.domain.Good;

import java.util.List;

public interface GoodService {
    List<Good> list_good(int name,int did);
}
