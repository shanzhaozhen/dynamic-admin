package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.common.ResourceType;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.mapper.ResourceMapper;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.utils.ResourceUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceDO> getResourceRoleListByType(Integer type) {
        return resourceMapper.selectResourceRoleListByTypeAndUserId(type, null);
    }

    @Override
    public List<ResourceDO> getResourcesByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        return resourceMapper.selectResourceRoleListByTypeAndUserId(ResourceType.MENU, userId);
    }

    @Override
    public List<AsyncRoute> getMenusByCurrentUser() {
        List<ResourceDO> resourceDOList = this.getResourcesByCurrentUser();
        return ResourceUtils.builtAsyncRouteTreeByResourceList(resourceDOList);
    }

    @Override
    public List<ResourceDO> getAllResourceTree() {
        List<ResourceDO> resourceDOList = resourceMapper.selectResourceList();
        return ResourceUtils.builtResourceTree(resourceDOList);
    }

    @Override
    public ResourceDO getResourceById(Long resourceId) {
        Assert.notNull(resourceId, "获取失败：没有找到该资源");
        ResourceDO resourceDO = resourceMapper.selectById(resourceId);
        Assert.notNull(resourceDO, "获取失败：没有找到该资源");
        return resourceDO;
    }

    @Override
    @Transactional
    public ResourceDO addResource(ResourceDO resourceDO) {
        resourceMapper.insert(resourceDO);
        return resourceDO;
    }

    @Override
    @Transactional
    public ResourceDO updateResource(ResourceDO resourceDO) {
        Assert.notNull(resourceDO.getId(), "更新失败：没有找到对应的资源");
        resourceMapper.updateById(resourceDO);
        return resourceDO;
    }

    @Override
    @Transactional
    public Boolean deleteResource(Long resourceId) {
        int count = resourceMapper.deleteById(resourceId);
        return count > 0;
    }

}
