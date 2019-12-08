package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.shanzhaozhen.dynamicadmin.dto.MenuDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.MenuDO;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuDO> {

    @Select("select r.id, r.name, r.description, type, r.path, r.pid, r.icon, " +
            "r.priority, r.hidden, r.always_show, r.no_cache, r.affix, r.breadcrumb " +
            "from sys_menu r " +
            "inner join sys_role_resource srr on srr.role_id = #{roleId} and r.id = srr.resource_id")
    List<MenuDTO> getMenuByRoleId(@Param("roleId") Long roleId);

    @Select("select r.id, r.name, r.description, type, r.path, r.pid, r.icon, " +
            "r.priority, r.hidden, r.always_show, r.no_cache, r.affix, r.breadcrumb " +
            "from sys_menu where type = #{type}")
    List<MenuDTO> getMenuListByType(@Param("type") int type);

    List<MenuDTO> getMenuRoleListByTypeAndUserId(@Param("type") int type, @Param("userId") Long userId);

    @Select("select * from sys_menu order by priority")
    List<MenuDTO> getMenuList();
}
