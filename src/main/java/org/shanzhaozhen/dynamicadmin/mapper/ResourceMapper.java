package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;

import java.util.List;

public interface ResourceMapper extends BaseMapper<ResourceDO> {

    @Select("select * from sys_resource r " +
            "inner join sys_role_resource srr on srr.role_id = #{roleId} and r.id = srr.resource_id")
    List<ResourceDO> selectResourceByRoleId(@Param("roleId") Long roleId);

    @Select("select * from sys_resource where type = #{type}")
    List<ResourceDO> selectResourceListByType(@Param("type") int type);

    List<ResourceDO> selectResourceRoleListByTypeAndUserId(@Param("type") int type, @Param("userId") Long userId);

    @Select("select * from sys_resource order by priority")
    List<ResourceDO> selectResourceList();
}
