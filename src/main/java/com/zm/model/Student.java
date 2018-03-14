package com.zm.model;

/**
 * @Package: com.zm.model.Student.java
 * @Description: 
 * @Company: null
 * @Copyright: null
 * All right reserved.
 * Author zhoumin
 * @date 2018/03/02 16:38
 * version v1.0.0
 */
public class Student {
    private Integer id;

    private String name;

    private Integer age;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}