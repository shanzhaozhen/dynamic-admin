package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.mapper.SysResourceMapper;
import org.shanzhaozhen.dynamicadmin.service.SysResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> selectSysResourceListByUsername(String username) {
        return sysResourceMapper.selectSysResourceListByType(1);
    }

    @Override
    public List<SysResource> selectSysResourceListByType(Integer type) {
        return sysResourceMapper.selectSysResourceListByType(type);
    }

    @Override
    public List<SysResource> selectSysResourceListJoinRoleByType(Integer type) {
        return sysResourceMapper.selectSysResourceListJoinRole();
    }

}
