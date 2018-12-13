package com.qianfeng.service.Impl;

import com.qianfeng.domain.Data;
import com.qianfeng.mapper.DataDao;
import com.qianfeng.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {


    @Autowired
    private DataDao dataDao;

    @Override
    public int getDid(String year, int i) {
        int did =0;

       try {
           did = dataDao.getDid(year, i);
       }catch (Exception e){

       }
       return did;
    }

    @Override
    public Data getByYearAndMonth(String year, String month) {
        return dataDao.getByYearAndMonth(year,month);
    }

    @Override
    public Data getByYear(String year) {
        return dataDao.getByYear(year);
    }

    @Override
    public int getByThree(String year, String month, int i) {
        int did =0;
        try {
            did =dataDao.getByThree(year,month,i);
        }catch (Exception e){

        }
        return did;
    }
}
