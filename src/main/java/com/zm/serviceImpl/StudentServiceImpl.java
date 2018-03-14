package com.zm.serviceImpl;

import com.zm.dto.StudentDto;
import com.zm.mapper.StudentMapper;
import com.zm.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2018-03-02 16:43
 **/
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<StudentDto> queryList() {
        List<StudentDto> list = studentMapper.queryList();
        return null;
    }
}
