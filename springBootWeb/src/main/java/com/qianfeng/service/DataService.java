package com.qianfeng.service;

import com.qianfeng.domain.Data;

public interface DataService {
    int getDid(String year, int i);

    Data getByYearAndMonth(String year, String mont);

    Data getByYear(String year);

    int getByThree(String year, String month, int i);
}
