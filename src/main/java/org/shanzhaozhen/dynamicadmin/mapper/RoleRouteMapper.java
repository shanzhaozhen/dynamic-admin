package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleRouteDO;

public interface RoleRouteMapper extends BaseMapper<RoleRouteDO> {

    @Delete("delete from sys_role_route where role_id = #{roleId}")
    int deleteByRoleId(Long roleId);
}
