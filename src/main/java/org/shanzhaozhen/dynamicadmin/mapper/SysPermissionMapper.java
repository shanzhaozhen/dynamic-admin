package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select("select p.* " +
            "from sys_user u " +
            "inner join sys_users_roles sur on u.id = sur.user_id " +
            "inner join sys_role r on r.id = sur.role_id " +
            "inner join sys_role_permission srp on r.id = srp.role_id " +
            "inner join sys_permission p on p.id = srp.permission_id " +
            "where u.username = ?1")
    List<SysPermission> selectByUsername(String username);

}
