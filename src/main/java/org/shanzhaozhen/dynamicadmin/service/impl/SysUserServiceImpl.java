package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.mapper.SysUserMapper;
import org.shanzhaozhen.dynamicadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser selectSysUserByUserId(String userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public SysUser selectSysUserByUsername(String username) {
        return sysUserMapper.selectSysUserByUsername(username);
    }
}
