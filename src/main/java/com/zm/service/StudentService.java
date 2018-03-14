package com.zm.service;

import com.zm.dto.StudentDto;

import java.util.List;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2018-03-02 16:42
 **/
public interface StudentService {
    List<StudentDto> queryList();
}
