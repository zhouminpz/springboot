package com.zm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2018-03-02 16:13
 **/
@Setter
@Getter
public class StudentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    private List<StuTeacherDto> list;
}
