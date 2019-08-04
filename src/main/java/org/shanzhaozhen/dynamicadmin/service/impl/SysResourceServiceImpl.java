package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.common.ResourceType;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.mapper.SysResourceMapper;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.SysResourceService;
import org.shanzhaozhen.dynamicadmin.utils.ResourceUtils;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> getSysResourceRoleListByType(Integer type) {
        return sysResourceMapper.selectSysResourceRoleListByTypeAndUserId(type, null);
    }

    @Override
    public List<SysResource> getSysResourcesByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        return sysResourceMapper.selectSysResourceRoleListByTypeAndUserId(ResourceType.MENU, userId);
    }

    @Override
    public List<AsyncRoute> getMenusByCurrentUser() {
        List<SysResource> resourceList = this.getSysResourcesByCurrentUser();
        return ResourceUtils.builtAsyncRouteTreeByResourceList(resourceList);
//        return ResultUtils.success(menuList);
    }

    @Override
    public Page<SysResource> getSysResourcePage() {
        List<SysResource> resourceList = this.getSysResourcesByCurrentUser();
        return null;
    }

}
