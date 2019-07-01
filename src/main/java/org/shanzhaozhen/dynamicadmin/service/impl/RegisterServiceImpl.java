package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.mapper.SysUserMapper;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.RegisterService;
import org.shanzhaozhen.dynamicadmin.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public ResultParam RegisterNewUser(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getUsername())) {
            return new ResultParam(false, "填写的用户名有误！");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            return new ResultParam(false, "填写的密码有误！");
        }
        int count = sysUserMapper.countByUsername(sysUser.getUsername());
        if (count > 0) {
            return new ResultParam(false, "注册失败，用户名已存在！");
        }
        SysUser newUser = new SysUser();
        newUser.setUsername(sysUser.getUsername());
        newUser.setPassword(PasswordUtils.encryption(sysUser.getPassword()));
        newUser.setAccountNonExpired(false);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        sysUserMapper.insert(newUser);
        return new ResultParam(true, "注册成功，等待管理员通过审核！");
    }

    @Override
    public Map<String, Boolean> checkUsername(String username) {
        int count = sysUserMapper.countByUsername(username);
        Map<String, Boolean> map = new HashMap<>();
        if (count > 0) {
            map.put("valid", false);
        } else {
            map.put("valid", true);
        }
        return map;
    }

}
