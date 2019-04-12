package com.schoolsell.service;

import com.schoolsell.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLoginService {

    /**
     * 根据用户ID删除用户
     * @param userid
     * @return
     */
    String deleteByPrimaryKey(String userid);

    /**
     * 添加用户
     * @param record
     * @return
     */
    String insert(User record);

    /**
     * 根据用户ID查找用户信息
     * @param userid
     * @return
     */
    User selectByPrimaryKey(String userid);

    /**
     * 查找所有用户信息
     * @return
     */
    List<User> selectAll();

    /**
     * 更新用户所有数据
     * @param record
     * @return
     */
    String updateByPrimaryKey(User record);

    /**
     *用户登录验证操作
     * @param userId
     * @param password
     * @return
     */
    String userLogin(String userId,String password);

}
