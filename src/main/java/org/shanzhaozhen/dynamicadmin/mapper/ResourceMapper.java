package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDo;

import java.util.List;

public interface ResourceMapper extends BaseMapper<ResourceDo> {

    @Select("select * from sys_resource r " +
            "inner join sys_role_resource srr on srr.role_id = #{roleId} and r.id = srr.resource_id")
    List<ResourceDo> selectResourceByRoleId(@Param("roleId") Long roleId);

    @Select("select * from sys_resource where type = #{type}")
    List<ResourceDo> selectResourceListByType(@Param("type") int type);

    List<ResourceDo> selectResourceRoleListByTypeAndUserId(@Param("type") int type, @Param("userId") Long userId);

    @Select("select * from sys_resource order by priority")
    List<ResourceDo> selectResourceList();
}
