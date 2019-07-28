package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysPermission;
import org.shanzhaozhen.dynamicadmin.mapper.SysPermissionMapper;
import org.shanzhaozhen.dynamicadmin.service.SysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> selectSysPermissionListByUsername(String username) {
        return sysPermissionMapper.selectSysPermissionListIsAuthorizationByUsername(username);
    }

    @Override
    public List<SysPermission> selectSysPermissionListIsAuthorization() {
        return sysPermissionMapper.selectSysPermissionListIsAuthorization();
    }

}
