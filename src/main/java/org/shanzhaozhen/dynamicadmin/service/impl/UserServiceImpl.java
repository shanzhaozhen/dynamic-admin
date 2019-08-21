package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;
import org.shanzhaozhen.dynamicadmin.mapper.UserMapper;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.utils.PasswordUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public UserDO getUserByUserId(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserDO getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public UserDO getCurrentUser() {
        UserDO userDO = userMapper.selectById(UserDetailsUtils.getUserId());
        Assert.notNull(userDO, "没有找到当前用户信息");
        return userDO;
    }

    @Override
    @Transactional
    public UserDO register(UserDO userDO) {
        Assert.hasText(userDO.getUsername(), "填写的账号不能为空！");
        Assert.hasText(userDO.getPassword(), "填写的密码不能为空！");
        Assert.state(this.isExistUser(userDO.getUsername()), "注册失败，该用户名已存在！");
        UserDO newUserDO = UserDO.builder()
                .username(userDO.getUsername())
                .password(PasswordUtils.encryption(userDO.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)      //默认开放账号
                .build();
        userMapper.insert(newUserDO);
        return newUserDO;
    }

    @Override
    public Boolean isExistUser(String username) {
        UserDO userDO = userMapper.selectUserByUsername(username);
        return userDO == null;
    }

    public Map<String, Object> getUserInfo() {
        UserDO userDO = this.getCurrentUser();
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", userDO.getNickname());
        map.put("avatar", userDO.getAvatar());
        map.put("introduction", userDO.getIntroduction());
        map.put("roles", UserDetailsUtils.getAuthorities());
        map.put("menus", resourceService.getMenusByCurrentUser());
        return map;
    }

}
