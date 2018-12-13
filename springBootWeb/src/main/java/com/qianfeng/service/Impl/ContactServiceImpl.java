package com.qianfeng.service.Impl;

import com.qianfeng.domain.Contact;
import com.qianfeng.domain.Data;
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
    public List<Contact> findZhu(int phone, int year) {
       return contactDao.findZhu(phone,year);

    }

    @Override
    public List<Contact> findBei(int phone, int did) {
        return contactDao.findBei(phone,did);

    }

    @Override
    public Data findZhu2(int uid, int did) {
        return contactDao.findZhu2(uid,did);
    }
}
