package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysResource;

import java.util.List;

public interface SysResourceService {

    List<SysResource> selectSysResourceListByUsername(String username);

    List<SysResource> selectSysResourceListByType(Integer type);

    List<SysResource> selectSysResourceListJoinRoleByType(Integer type);
}
