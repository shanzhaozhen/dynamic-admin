package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

    List<SysPermission> selectSysPermissionListByUsername(String username);

    List<SysPermission> selectSysPermissionListIsAuthorization();
}
