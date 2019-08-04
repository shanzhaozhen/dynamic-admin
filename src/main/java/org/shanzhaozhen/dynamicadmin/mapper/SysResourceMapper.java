package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {

    @Select("select * from sys_resource r " +
            "inner join sys_role_resource srr on srr.role_id = #{roleId} and r.id = srr.resource_id")
    List<SysResource> selectSysResourceByRoleId(@Param("roleId") Long roleId);

    @Select("select * from sys_resource where type = #{type}")
    List<SysResource> selectSysResourceListByType(@Param("type") int type);

    List<SysResource> selectSysResourceRoleListByTypeAndUserId(@Param("type") int type, @Param("userId") Long userId);

}
