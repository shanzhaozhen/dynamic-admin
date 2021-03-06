package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.domain.sys.ResourceDO;

import java.util.List;

public interface ResourceMapper extends BaseMapper<ResourceDO> {

    @Select("select r.id, r.name, r.path, r.type, r.pid, r.priority, r.description, " +
            "r.create_by, r.created_date, r.last_modified_by, r.last_modified_date " +
            "from sys_resource r " +
            "inner join sys_role_resource srr on srr.role_id = #{roleId} and r.id = srr.resource_id")
    List<ResourceDTO> getResourceByRoleId(@Param("roleId") Long roleId);

    List<ResourceDTO> getResourceListByType(@Param("type") Integer type);

    List<ResourceDTO> getResourceRoleListByTypeAndUserId(@Param("type") Integer type, @Param("userId") Long userId);

    @Select("select id, name, path, type, pid, priority, description, " +
            "create_by, created_date, last_modified_by, last_modified_date " +
            "from sys_resource order by priority")
    List<ResourceDTO> getResourceList();
}
