package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;

import java.util.Map;

public interface SysUserService {

    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    SysUser getSysUserByUserId(String userId);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    SysUser getSysUserByUsername(String username);

    /**
     * 注册新用户
     * @param sysUser
     * @return
     */
    Map<String, Object> register(SysUser sysUser);

    /**
     * 检查用户名是否已存在
     * @param username
     * @return
     */
    Map<String, Boolean> checkUsername(String username);
}
