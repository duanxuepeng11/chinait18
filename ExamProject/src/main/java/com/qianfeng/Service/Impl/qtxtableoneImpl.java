package com.qianfeng.Service.Impl;

import com.qianfeng.domain.qtxtableone;
import com.qianfeng.Service.Inter.qtxtableoneInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class qtxtableoneImpl implements qtxtableoneInter {

    @Autowired
    private com.qianfeng.Dao.qtxtableoneDao qtxtableoneDao;

    @Override
    public List<qtxtableone> selectAll(String examinee_num) {
        return qtxtableoneDao.selectAll(examinee_num);
    }
}
