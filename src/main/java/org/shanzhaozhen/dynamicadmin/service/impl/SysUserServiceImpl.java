package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.mapper.SysUserMapper;
import org.shanzhaozhen.dynamicadmin.param.JWTUser;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.SysUserService;
import org.shanzhaozhen.dynamicadmin.utils.PasswordUtils;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
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
    public SysUser getCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        return sysUserMapper.selectById(userId);
    }

    @Override
    public ResultParam register(SysUser sysUser) {

        if (StringUtils.isEmpty(sysUser.getUsername())) {
            return ResultUtils.failure("填写的用户名有误！");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            return ResultUtils.failure("填写的密码有误！");
        }
        int count = sysUserMapper.countByUsername(sysUser.getUsername());
        if (count > 0) {
            return ResultUtils.failure("注册失败，该用户名已存在！");
        }
        SysUser newUser = SysUser.builder()
                .username(sysUser.getUsername())
                .password(PasswordUtils.encryption(sysUser.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)      //默认开放账号
                .build();
        sysUserMapper.insert(newUser);
        return ResultUtils.success("注册成功，等待管理员通过审核！");
    }

    @Override
    public ResultParam checkUsername(String username) {
        int count = sysUserMapper.countByUsername(username);
        if (count > 0) {
            return ResultUtils.failure("该用户名已被注册");
        }
        return ResultUtils.success("该用户名可以使用");
    }

    @Override
    public ResultParam getUserInfo() {
        JWTUser jwtUser = UserDetailsUtils.getJWTUser();
        return ResultUtils.success("获取成功", jwtUser);
    }

}
