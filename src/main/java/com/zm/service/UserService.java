package com.zm.service;

import com.zm.model.User;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2017-12-19 17:58
 **/
public interface UserService {
    Integer addUser(User user);
}
