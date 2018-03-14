package com.zm.mapper;

import com.zm.model.StuTeacher;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.zm.mapper.StuTeacherMapper.java
 * @Description: 
 * @Company: null
 * @Copyright: null
 * All right reserved.
 * Author zhoumin
 * @date 2018/03/02 16:38
 * version v1.0.0
 */
@Repository
public interface StuTeacherMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zhoumin
     * @Date 2018/03/02 16:38
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zhoumin
     * @Date 2018/03/02 16:38
     * @param record
     * @return int
     * @throws []
     */
    int insert(StuTeacher record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zhoumin
     * @Date 2018/03/02 16:38
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(StuTeacher record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zhoumin
     * @Date 2018/03/02 16:38
     * @param id
     * @return com.zm.model.StuTeacher
     * @throws []
     */
    StuTeacher selectByPrimaryKey(Integer id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zhoumin
     * @Date 2018/03/02 16:38
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(StuTeacher record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zhoumin
     * @Date 2018/03/02 16:38
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(StuTeacher record);
}