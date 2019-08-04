package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select * from sys_role r " +
            "inner join sys_role_resource srr on srr.resource_id = #{resourceId} and r.id = srr.role_id")
    List<SysRole> selectSysRoleByResourceId(@Param("resourceId") Long resourceId);

    @Select("select * from sys_role r " +
            "inner join sys_user_role sur on sur.user_id = #{userId} and r.id = sur.role_id")
    List<SysRole> selectSysRoleByUserId(@Param("userId") Long userId);

}
