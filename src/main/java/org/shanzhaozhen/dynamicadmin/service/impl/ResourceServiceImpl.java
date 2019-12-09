package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.shanzhaozhen.dynamicadmin.common.sys.ResourceType;
import org.shanzhaozhen.dynamicadmin.converter.ResourceConverter;
import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.mapper.ResourceMapper;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.utils.ResourceUtils;
import org.shanzhaozhen.dynamicadmin.utils.MyBeanUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
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
    public List<AsyncRoute> getResourcesByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        List<ResourceDTO> resourceDTOList = resourceMapper.getResourceRoleListByTypeAndUserId(ResourceType.API.getValue(), userId);
        return ResourceUtils.builtAsyncRouteTreeByResourceList(resourceDTOList);
    }

    @Override
    public List<ResourceDTO> getAllResourceTree() {
        List<ResourceDTO> resourceDTOList = resourceMapper.getResourceList();
        return ResourceUtils.builtResourceTree(resourceDTOList);
    }

    @Override
    public ResourceDTO getResourceById(Long resourceId) {
        ResourceDO resourceDO = resourceMapper.selectById(resourceId);
        Assert.notNull(resourceDO, "获取失败：没有找到该资源或已被删除");
        return ResourceConverter.doToDTO(resourceDO);
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
        Assert.notNull(resourceDTO.getId(), "资源id不能为空");
        ResourceDO resourceDO = resourceMapper.selectById(resourceDTO.getId());
        Assert.notNull(resourceDO, "更新失败：没有找到该资源或已被删除");
        MyBeanUtils.copyPropertiesExcludeMeta(resourceDTO, resourceDO);
        resourceMapper.updateById(resourceDO);
        return resourceDTO;
    }

    @Override
    @Transactional
    public Boolean deleteResource(Long resourceId) {
        return SqlHelper.retBool(resourceMapper.deleteById(resourceId));
    }

}
