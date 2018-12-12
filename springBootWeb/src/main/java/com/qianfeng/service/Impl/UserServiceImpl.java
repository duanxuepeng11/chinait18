package com.qianfeng.service.Impl;

import com.qianfeng.domain.User;
import com.qianfeng.mapper.UserDao;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByPhone(String phone) {

          User user = userDao.findByPhone(phone);
        System.out.println("user的属性"+user.toString());
        if(user.getUid() <=0){
            return null;
        }else {
            return user;
        }
    }
}
