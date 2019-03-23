package com.api.service;

import com.api.dao.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 李显琪 on 2019/3/23.
 */
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String getUserName(){
        return userMapper.getAge()+"";
    }
}
