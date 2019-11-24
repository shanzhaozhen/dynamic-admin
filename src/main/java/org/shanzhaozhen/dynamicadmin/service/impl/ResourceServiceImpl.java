package org.shanzhaozhen.dynamicadmin.service.impl;

import org.shanzhaozhen.dynamicadmin.common.ResourceType;
import org.shanzhaozhen.dynamicadmin.converter.ResourceConverter;
import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.mapper.ResourceMapper;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
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
    public List<ResourceDTO> getResourceRoleListByType(Integer type) {
        return resourceMapper.getResourceRoleListByTypeAndUserId(type, null);
    }

    @Override
    public List<AsyncRoute> getMenusByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        List<ResourceDTO> resourceDTOList = resourceMapper.getResourceRoleListByTypeAndUserId(ResourceType.MENU, userId);
        return ResourceUtils.builtAsyncRouteTreeByResourceList(resourceDTOList);
    }

    @Override
    public List<ResourceDTO> getAllResourceTree() {
        List<ResourceDTO> resourceDTOList = resourceMapper.getResourceList();
        return ResourceUtils.builtResourceTree(resourceDTOList);
    }

    @Override
    public ResourceDTO getResourceById(Long resourceId) {
        Assert.notNull(resourceId, "获取失败：没有找到该资源");
        ResourceDO resourceDO = resourceMapper.selectById(resourceId);
        Assert.notNull(resourceDO, "获取失败：没有找到该资源");
        ResourceDTO resourceDTO = ResourceConverter.doToDTO(resourceDO);
        return resourceDTO;
    }

    @Override
    @Transactional
    public ResourceDTO addResource(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = ResourceConverter.dtoToDO(resourceDTO);
        resourceMapper.insert(resourceDO);
        return resourceDTO;
    }

    @Override
    @Transactional
    public ResourceDTO updateResource(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = ResourceConverter.dtoToDO(resourceDTO);
        resourceMapper.updateById(resourceDO);
        return resourceDTO;
    }

    @Override
    @Transactional
    public Boolean deleteResource(Long resourceId) {
        int count = resourceMapper.deleteById(resourceId);
        return count > 0;
    }

}
