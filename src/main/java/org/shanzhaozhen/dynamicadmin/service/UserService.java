package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;

import java.util.Map;

public interface UserService {

    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    UserDO getUserByUserId(String userId);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    UserDO getUserByUsername(String username);


    /**
     * 获取当前用户
     */
    UserDO getCurrentUser();

    /**
     * 注册新用户
     * @param userDO
     * @return
     */
    UserDO register(UserDO userDO);

    /**
     * 检查用户名是否已存在
     * @param username
     * @return
     */
    Boolean isExistUser(String username);

    /**
     * 获取当前用户的主要信息
     * @return
     */
    Map<String, Object> getUserInfo();
}
