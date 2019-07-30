package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.mapper.SysUserMapper;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.RegisterService;
import org.shanzhaozhen.dynamicadmin.utils.PasswordUtils;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public ResultParam RegisterNewUser(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getUsername())) {
            return ResultUtils.failure("填写的用户名有误！");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            return ResultUtils.failure("填写的密码有误！");
        }
        int count = sysUserMapper.countByUsername(sysUser.getUsername());
        if (count > 0) {
            return ResultUtils.failure("注册失败，用户名已存在！");
        }
        SysUser newUser = SysUser.builder()
                .username(sysUser.getUsername())
                .password(PasswordUtils.encryption(sysUser.getPassword()))
                .accountNonExpired(false)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
            .build();
        sysUserMapper.insert(newUser);
        return ResultUtils.success("注册成功，等待管理员通过审核！");
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
