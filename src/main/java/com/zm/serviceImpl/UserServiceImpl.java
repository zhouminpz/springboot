package com.zm.serviceImpl;

import com.zm.mapper.UserMapper;
import com.zm.model.User;
import com.zm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2017-12-19 17:59
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) {
        if (null == user){
            return 0;
        }
        Integer id = 0;
        try {
            id = userMapper.insert(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
