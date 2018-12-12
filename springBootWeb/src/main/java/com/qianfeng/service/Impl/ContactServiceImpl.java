package com.qianfeng.service.Impl;

import com.qianfeng.domain.Contact;
import com.qianfeng.mapper.ContactDao;
import com.qianfeng.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDao contactDao;

    @Override
    public List<Contact> findThree(String phone,String year,String month) {
        return contactDao.findThree(phone,year,month);
    }

    @Override
    public Contact findZhu(int phone, int year) {
       Contact cc = contactDao.findZhu(phone,year);
       if(cc ==null){
           return null;
       }else{
           return cc;
       }
    }

    @Override
    public Contact findBei(int phone, int did) {
        Contact cc = contactDao.findBei(phone,did);
        if(cc ==null){
            return null;
        }else{
            return cc;
        }
    }
}
