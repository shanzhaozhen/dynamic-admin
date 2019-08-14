package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.common.ResourceType;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDo;
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
    public List<ResourceDo> getResourceRoleListByType(Integer type) {
        return resourceMapper.selectResourceRoleListByTypeAndUserId(type, null);
    }

    @Override
    public List<ResourceDo> getResourcesByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        return resourceMapper.selectResourceRoleListByTypeAndUserId(ResourceType.MENU, userId);
    }

    @Override
    public List<AsyncRoute> getMenusByCurrentUser() {
        List<ResourceDo> resourceDoList = this.getResourcesByCurrentUser();
        return ResourceUtils.builtAsyncRouteTreeByResourceList(resourceDoList);
    }

    @Override
    public List<ResourceDo> getAllResourceTree() {
        List<ResourceDo> resourceDoList = resourceMapper.selectResourceList();
        return ResourceUtils.builtResourceTree(resourceDoList);
    }

    @Override
    public ResourceDo getResourceById(Long resourceId) {
        Assert.notNull(resourceId, "获取失败：没有找到该资源");
        ResourceDo resourceDo = resourceMapper.selectById(resourceId);
        Assert.notNull(resourceDo, "获取失败：没有找到该资源");
        return resourceDo;
    }

    @Override
    @Transactional
    public ResourceDo addResource(ResourceDo resourceDo) {
        resourceMapper.insert(resourceDo);
        return resourceDo;
    }

    @Override
    @Transactional
    public ResourceDo updateResource(ResourceDo resourceDo) {
        Assert.notNull(resourceDo.getId(), "更新失败：没有找到对应的资源");
        resourceMapper.updateById(resourceDo);
        return resourceDo;
    }

    @Override
    @Transactional
    public Boolean deleteResource(Long resourceId) {
        int count = resourceMapper.deleteById(resourceId);
        return count > 0;
    }

}
