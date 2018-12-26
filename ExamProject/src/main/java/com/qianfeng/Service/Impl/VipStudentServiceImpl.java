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
        int zong = 0;
        if(select_value.equals("1")){
            zong= vipDaoMapping.vipCount();
        }else {
            zong = vipDaoMapping.petCount();
        }

        System.out.println("service : " + pageCode + " " + pageSizes + " " + select_value);
        PageBean<VIPStudent> page = new PageBean<VIPStudent>();
        page.setPageCode(pageCode);
        page.setPageSize(pageSizes);
        page.setTotalCount(zong);
        page.setTotalPage();
        System.out.println("总 ：" + page.getTotalPage());

        if(select_value.equals("1")){
            // vip
            List<VIPStudent> allVip = vipDaoMapping.findAllVip(pageCode == 1 ? pageCode : (pageCode-1)*pageSizes, pageSizes);
            for(VIPStudent stu : allVip){
                System.out.println(stu);
            }
            page.setBeanList(allVip);
        }else if(select_value.equals("2")){
            // 末班
            List<VIPStudent> allPettyOfficial = vipDaoMapping.findAllPettyOfficial(pageCode == 1 ? pageCode : (pageCode-1)*pageSizes, pageSizes);
            for(VIPStudent stu : allPettyOfficial){
                System.out.println(stu);
            }
            page.setBeanList(allPettyOfficial);
        }




        return page;
    }

}
