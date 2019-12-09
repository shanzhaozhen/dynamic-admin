package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;

import java.util.List;

public interface ResourceMapper extends BaseMapper<ResourceDO> {

    @Select("select r.id, r.name, r.description, type, r.path, r.pid, r.icon, " +
            "r.priority, r.hidden, r.always_show, r.no_cache, r.affix, r.breadcrumb " +
            "from sys_route r " +
            "inner join sys_role_resource srr on srr.role_id = #{roleId} and r.id = srr.resource_id")
    List<ResourceDTO> getResourceByRoleId(@Param("roleId") Long roleId);

    @Select("select r.id, r.name, r.description, type, r.path, r.pid, r.icon, " +
            "r.priority, r.hidden, r.always_show, r.no_cache, r.affix, r.breadcrumb " +
            "from sys_route where type = #{type}")
    List<ResourceDTO> getResourceListByType(@Param("type") int type);

    List<ResourceDTO> getResourceRoleListByTypeAndUserId(@Param("type") int type, @Param("userId") Long userId);

    @Select("select * from sys_route order by priority")
    List<ResourceDTO> getResourceList();
}
