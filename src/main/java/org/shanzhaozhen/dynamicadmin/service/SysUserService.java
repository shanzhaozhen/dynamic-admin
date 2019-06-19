package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;

public interface SysUserService {

    SysUser selectSysUserByUserId(String userId);

    SysUser selectSysUserByUsername(String username);

}
