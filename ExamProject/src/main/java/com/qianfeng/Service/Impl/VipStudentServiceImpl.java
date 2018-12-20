package com.qianfeng.Service.Impl;

import com.qianfeng.Dao.VipDaoMapping;
import com.qianfeng.Service.Inter.VipStudentService;
import com.qianfeng.domain.VIPStudent;
import com.qianfeng.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipStudentServiceImpl implements VipStudentService {

    @Autowired
    VipDaoMapping vipDaoMapping;

    @Override
    public PageBean<VIPStudent> findAll(Integer pageCode, Integer pageSizes, String select_value) {

        PageBean<VIPStudent> page = new PageBean<VIPStudent>();
        page.setPageCode(pageCode);
        page.setPageSize(pageSizes);

        if(select_value.equals("1")){
            // vip
            List<VIPStudent> allVip = vipDaoMapping.findAllVip(pageCode, pageSizes);
            page.setBeanList(allVip);
        }else {
            // 末班
            List<VIPStudent> allPettyOfficial = vipDaoMapping.findAllPettyOfficial(pageCode, pageSizes);
            page.setBeanList(allPettyOfficial);
        }

        return page;
    }

}
