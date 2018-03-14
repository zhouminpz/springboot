package com.zm.model;

/**
 * @Package: com.zm.model.StuTeacher.java
 * @Description: 
 * @Company: null
 * @Copyright: null
 * All right reserved.
 * Author zhoumin
 * @date 2018/03/02 16:38
 * version v1.0.0
 */
public class StuTeacher {
    private Integer id;

    private Integer stuId;

    private Integer teacherId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return stu_id
     */
    public Integer getStuId() {
        return stuId;
    }

    /**
     * @param stuId
     */
    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    /**
     * @return teacher_id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}