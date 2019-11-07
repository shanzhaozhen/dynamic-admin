package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.dto.UserDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;
import org.shanzhaozhen.dynamicadmin.form.UserForm;

import java.util.Map;

public interface UserService {

    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    UserDTO getUserByUserId(Long userId);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    UserDTO getUserByUsername(String username);


    /**
     * 获取当前用户
     */
    UserDTO getCurrentUser();

    /**
     * 注册新用户
     * @param userDTO
     * @return
     */
    Long register(UserDTO userDTO);

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
