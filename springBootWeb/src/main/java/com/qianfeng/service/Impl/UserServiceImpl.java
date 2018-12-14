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

    @Override
    public User findByUid(int uid_you) {
        return userDao.findByUid(uid_you);
    }

    @Override
    public int findUser(String name) {
        System.out.println("service");
        int id = 0;
        try{
            id = userDao.findUser(name);
        }catch (Exception e){}

        return id;
    }

    @Override
    public int reUser(String username, String pass) {
        int i = userDao.reUser(username, pass);
        if(i > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public int logins(String username, String password) {
        int i = 0;
        try {
            i = userDao.logins(username, password);
        }catch (Exception e){

        }
        System.out.println("==="+i);
        return i;
    }

}
