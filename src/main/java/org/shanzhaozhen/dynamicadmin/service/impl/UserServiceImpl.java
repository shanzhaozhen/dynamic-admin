package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.dto.UserDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;
import org.shanzhaozhen.dynamicadmin.form.UserForm;
import org.shanzhaozhen.dynamicadmin.mapper.UserMapper;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.beans.BeanUtils;
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
    public UserDTO getUserByUserId(Long userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public UserDTO getCurrentUser() {
        UserDTO userDTO = userMapper.getUserByUserId(UserDetailsUtils.getUserId());
        Assert.notNull(userDTO, "没有找到当前用户信息");
        return userDTO;
    }

    @Override
    @Transactional
    public Long register(UserDTO userDTO) {
        Assert.state(this.isExistUser(userDTO.getUsername()), "注册失败，该用户名已存在！");
        UserDO newUser = new UserDO();
        BeanUtils.copyProperties(userDTO, newUser);
        userMapper.insert(newUser);
        return newUser.getId();
    }

    @Override
    public Boolean isExistUser(String username) {
        UserDO userDO = userMapper.selectUserByUsername(username);
        return userDO == null;
    }

    public Map<String, Object> getUserInfo() {
        UserDTO userDTO = this.getCurrentUser();
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", userDTO.getNickname());
        map.put("avatar", userDTO.getAvatar());
        map.put("introduction", userDTO.getIntroduction());
        map.put("roles", UserDetailsUtils.getAuthorities());
        map.put("menus", resourceService.getMenusByCurrentUser());
        return map;
    }

}
