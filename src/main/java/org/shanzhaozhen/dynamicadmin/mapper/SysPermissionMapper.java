package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select("select * from sys_permission where permission_name is not null")
    List<SysPermission> selectSysPermissionListIsAuthorization();

    @Select("select p.* " +
            "from sys_user u " +
            "inner join sys_user_role sur on u.id = sur.user_id " +
            "inner join sys_role r on r.id = sur.role_id " +
            "inner join sys_role_permission srp on r.id = srp.role_id " +
            "inner join sys_permission p on p.id = srp.permission_id " +
            "where permission_name is not null and u.username = #{userName}")
    List<SysPermission> selectSysPermissionListIsAuthorizationByUsername(String username);

}
