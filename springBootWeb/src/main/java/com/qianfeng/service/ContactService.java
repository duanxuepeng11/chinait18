package com.qianfeng.service;

import com.qianfeng.domain.Contact;
import com.qianfeng.domain.Data;

import java.util.List;

public interface ContactService {
    List<Contact> findThree(String phone,String year,String month);

    List<Contact> findZhu(int phone, int year);

    List<Contact> findBei(int phone, int did);

    Data findZhu2(int uid, int did);
}
