package com.zm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2018-03-02 16:27
 **/
@Setter
@Getter
public class StuTeacherDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer stuId;

    private Integer teacherId;
}
