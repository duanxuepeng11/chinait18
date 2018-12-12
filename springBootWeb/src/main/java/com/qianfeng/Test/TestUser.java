package com.qianfeng.Test;

import com.qianfeng.domain.User;
import com.qianfeng.service.Impl.UserServiceImpl;
import com.qianfeng.service.UserService;

import java.util.List;

public class TestUser {
    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();
        List<User> all = userServiceImpl.findAll();
        for(User us : all){
            System.out.println(us.toString());
        }

    }
}
