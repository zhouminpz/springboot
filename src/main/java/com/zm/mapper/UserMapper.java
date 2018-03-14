package com.zm.mapper;

import com.zm.model.User;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.zm.mapper.UserMapper.java
 * @Description: 
 * @Company: null
 * @Copyright: null
 * All right reserved.
 * Author zhoumin
 * @date 2017/12/19 17:53
 * version v1.0.0
 */
@Repository(value="userMapper")
public interface UserMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zhoumin
     * @Date 2017/12/19 17:53
     * @param userId
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zhoumin
     * @Date 2017/12/19 17:53
     * @param record
     * @return int
     * @throws []
     */
    int insert(User record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zhoumin
     * @Date 2017/12/19 17:53
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(User record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zhoumin
     * @Date 2017/12/19 17:53
     * @param userId
     * @return com.zm.model.User
     * @throws []
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zhoumin
     * @Date 2017/12/19 17:53
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zhoumin
     * @Date 2017/12/19 17:53
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(User record);
}