package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysRole;

import java.util.List;

public interface SysRoleService {

    /**
     * 通过用户id获取用户角色
     * @param userId
     * @return
     */
    List<SysRole> getSysRolesByUserId(Long userId);

}
