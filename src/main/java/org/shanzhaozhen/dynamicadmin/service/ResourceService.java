package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;

import java.util.List;

public interface ResourceService {

    /**
     * 通过 ResourceType 类型获取所有的Resource（多对多含有角色信息）
     * @param type
     * @return
     */
    List<ResourceDO> getResourceRoleListByType(Integer type);

    /**
     * 通过当前用户的信息获取前端可访问的路由
     * @return
     */
    List<ResourceDO> getResourcesByCurrentUser();

    /**
     * 通过当前用户的信息生成对应的前端路由
     * @return
     */
    List<AsyncRoute> getMenusByCurrentUser();

    /**
     * 获取所有资源的树形结构
     * @return
     */
    List<ResourceDO> getAllResourceTree();

    /**
     * 通过资源id获取资源实体
     * @param resourceId
     * @return
     */
    ResourceDO getResourceById(Long resourceId);

    /**
     * 增加资源
     * @param resourceDO
     * @return
     */
    ResourceDO addResource(ResourceDO resourceDO);

    /**
     * 修改资源
     * @param resourceDO
     * @return
     */
    ResourceDO updateResource(ResourceDO resourceDO);

    /**
     * 删除资源(通过资源id删除)
     * @param resourceId
     * @return true 则删除成功，否则删除失败
     */
    Boolean deleteResource(Long resourceId);

}
