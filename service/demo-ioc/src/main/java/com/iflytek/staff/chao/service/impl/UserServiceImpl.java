package com.iflytek.staff.chao.service.impl;

import com.iflytek.staff.chao.service.UserService;
import org.springframework.stereotype.Service;

//@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public Integer getUserCount(String username) {
        System.out.println(username);
        return 2;
    }
}
