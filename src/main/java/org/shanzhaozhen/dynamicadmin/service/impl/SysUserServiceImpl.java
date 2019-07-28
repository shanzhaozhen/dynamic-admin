package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.mapper.SysUserMapper;
import org.shanzhaozhen.dynamicadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser getSysUserByUserId(String userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public SysUser getSysUserByUsername(String username) {
        return sysUserMapper.selectSysUserByUsername(username);
    }

    @Override
    public Map<String, Object> register(SysUser sysUser) {
        return null;
    }

    @Override
    public Map<String, Boolean> checkUsername(String username) {
        sysUserMapper.countByUsername(username);
        return null;
    }

}
