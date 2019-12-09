package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RouteDO;

import java.util.List;

public interface RouteMapper extends BaseMapper<RouteDO> {

    @Select("select m.id, m.name, m.path, m.pid, m.component, m.redirect, m.title, m.icon, m.priority, " +
            "m.hidden, m.always_show, m.no_cache, m.affix, m.breadcrumb, m.props, m.description " +
            "from sys_route m " +
            "inner join sys_role_route srm on srm.role_id = #{roleId} and m.id = srm.route_id")
    List<RouteDTO> getRouteByRoleId(@Param("roleId") Long roleId);

    @Select("select id, name, path, pid, component, redirect, title, icon, priority, " +
            "hidden, always_show, no_cache, affix, breadcrumb, props, description " +
            "from sys_route order by priority")
    List<RouteDTO> getRouteList();

    List<RouteDTO> getRouteRoleListByUserId(@Param("userId") Long userId);
}
