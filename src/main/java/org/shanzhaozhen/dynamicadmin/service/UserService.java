package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDo;

import java.util.Map;

public interface UserService {

    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    UserDo getUserByUserId(String userId);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    UserDo getUserByUsername(String username);


    /**
     * 获取当前用户
     */
    UserDo getCurrentUser();

    /**
     * 注册新用户
     * @param userDo
     * @return
     */
    UserDo register(UserDo userDo);

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
