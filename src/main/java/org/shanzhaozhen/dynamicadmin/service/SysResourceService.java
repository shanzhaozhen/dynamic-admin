package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;

import java.util.List;

public interface SysResourceService {

    /**
     * 通过 ResourceType 类型获取所有的SysResource（多对多含有角色信息）
     * @param type
     * @return
     */
    List<SysResource> getSysResourceRoleListByType(Integer type);

    /**
     * 通过当前用户的信息获取前端可访问的路由
     * @return
     */
    List<SysResource> getSysResourcesByCurrentUser();

    /**
     * 通过当前用户的信息生成对应的前端路由
     * @return
     */
    List<AsyncRoute> getMenusByCurrentUser();

    /**
     * 获取所有资源的树形结构
     * @return
     */
    ResultParam getAllSysResourceTree();
}
