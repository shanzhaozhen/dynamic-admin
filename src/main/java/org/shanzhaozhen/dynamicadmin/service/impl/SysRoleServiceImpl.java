package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysRole;
import org.shanzhaozhen.dynamicadmin.mapper.SysRoleMapper;
import org.shanzhaozhen.dynamicadmin.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getSysRolesByUserId(Long userId) {
        return sysRoleMapper.selectSysRoleByUserId(userId);
    }

}
