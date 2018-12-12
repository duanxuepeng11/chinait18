package com.qianfeng.service;

import com.qianfeng.domain.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findThree(String phone,String year,String month);

    Contact findZhu(int phone, int year);

    Contact findBei(int phone, int did);
}
