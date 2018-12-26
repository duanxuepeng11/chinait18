package com.qianfeng.Service.Impl;

import com.qianfeng.Service.Inter.gtxtableoneInter;
import com.qianfeng.domain.gtxtableone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class gtxtableoneImpl implements gtxtableoneInter {

    @Autowired
    private com.qianfeng.Dao.gtxtableoneDao gtxtableoneDao;

    @Override
    public List<gtxtableone> selectAll(String examinee_num) {
        return gtxtableoneDao.selectAll(examinee_num);
    }
}
