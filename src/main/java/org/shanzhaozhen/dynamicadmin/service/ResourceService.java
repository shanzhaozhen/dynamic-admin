package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;

import java.util.List;

public interface ResourceService {

    /**
     * 通过 ResourceType 类型获取所有的Resource（多对多含有角色信息）
     * @param type
     * @return
     */
    List<ResourceDTO> getResourceRoleListByType(Integer type);

    /**
     * 通过当前用户的信息生成对应的前端路由
     * @return
     */
    List<AsyncRoute> getResourcesByCurrentUser();

    /**
     * 获取所有资源的树形结构
     * @return
     */
    List<ResourceDTO> getAllResourceTree();

    /**
     * 通过资源id获取资源实体
     * @param resourceId
     * @return
     */
    ResourceDTO getResourceById(Long resourceId);

    /**
     * 增加资源
     * @param resourceDTO
     * @return
     */
    ResourceDTO addResource(ResourceDTO resourceDTO);

    /**
     * 修改资源
     * @param resourceDTO
     * @return
     */
    ResourceDTO updateResource(ResourceDTO resourceDTO);

    /**
     * 删除资源(通过资源id删除)
     * @param resourceId
     * @return true 则删除成功，否则删除失败
     */
    Boolean deleteResource(Long resourceId);

}
