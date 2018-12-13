package com.qianfeng.service;

import com.qianfeng.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByPhone(String phone);

    User findByUid(int uid_you);
}
