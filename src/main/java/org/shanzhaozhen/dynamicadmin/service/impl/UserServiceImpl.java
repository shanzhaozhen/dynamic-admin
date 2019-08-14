package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDo;
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
    public UserDo getUserByUserId(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserDo getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public UserDo getCurrentUser() {
        UserDo userDo = userMapper.selectById(UserDetailsUtils.getUserId());
        Assert.notNull(userDo, "没有找到当前用户信息");
        return userDo;
    }

    @Override
    @Transactional
    public UserDo register(UserDo userDo) {
        Assert.hasText(userDo.getUsername(), "填写的账号不能为空！");
        Assert.hasText(userDo.getPassword(), "填写的密码不能为空！");
        Assert.state(this.isExistUser(userDo.getUsername()), "注册失败，该用户名已存在！");
        UserDo newUserDo = UserDo.builder()
                .username(userDo.getUsername())
                .password(PasswordUtils.encryption(userDo.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)      //默认开放账号
                .build();
        userMapper.insert(newUserDo);
        return newUserDo;
    }

    @Override
    public Boolean isExistUser(String username) {
        UserDo userDo = userMapper.selectUserByUsername(username);
        return userDo == null;
    }

    public Map<String, Object> getUserInfo() {
        UserDo userDo = this.getCurrentUser();
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", userDo.getNickname());
        map.put("avatar", userDo.getAvatar());
        map.put("introduction", userDo.getIntroduction());
        map.put("roles", UserDetailsUtils.getAuthorities());
        map.put("menus", resourceService.getMenusByCurrentUser());
        return map;
    }

}
