package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.entity.SysRole;
import org.shanzhaozhen.dynamicadmin.entity.SysRoleResource;

import java.util.List;

public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    List<SysRole> selectSysRoleByResourceId(Long resourceId);

    List<SysResource> selectSysResourceByRoleId(Long roleId);

}
