package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;
import org.shanzhaozhen.dynamicadmin.mapper.UserMapper;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;
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
    private UserMapper userMapper;

    @Override
    @Transactional
    public ResultObject registerNewUser(UserDO userDO) {
        if (StringUtils.isEmpty(userDO.getUsername())) {
            return ResultUtils.failure("填写的用户名有误！");
        }
        if (StringUtils.isEmpty(userDO.getPassword())) {
            return ResultUtils.failure("填写的密码有误！");
        }
        int count = userMapper.countByUsername(userDO.getUsername());
        if (count > 0) {
            return ResultUtils.failure("注册失败，用户名已存在！");
        }
        UserDO newUserDO = UserDO.builder()
                .username(userDO.getUsername())
                .password(PasswordUtils.encryption(userDO.getPassword()))
                .accountNonExpired(false)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
            .build();
        userMapper.insert(newUserDO);
        return ResultUtils.success("注册成功，等待管理员通过审核！");
    }

    @Override
    public Map<String, Boolean> checkUsername(String username) {
        int count = userMapper.countByUsername(username);
        Map<String, Boolean> map = new HashMap<>();
        if (count > 0) {
            map.put("valid", false);
        } else {
            map.put("valid", true);
        }
        return map;
    }

}
