package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDo;

import java.util.List;

public interface RoleMapper extends BaseMapper<RoleDo> {

    @Select("select * from sys_role r " +
            "inner join sys_role_resource srr on srr.resource_id = #{resourceId} and r.id = srr.role_id")
    List<RoleDo> selectRoleByResourceId(@Param("resourceId") Long resourceId);

    @Select("select * from sys_role r " +
            "inner join sys_user_role sur on sur.user_id = #{userId} and r.id = sur.role_id")
    List<RoleDo> selectRoleByUserId(@Param("userId") Long userId);

    @Select("select * from sys_role where name like '%${keyword}%' or identification like '%${keyword}%' or description like '%${keyword}%'")
    Page<RoleDo> selectRolePage(Page page, String keyword);

    RoleDo selectRoleByRoleId(@Param("roleId") Long roleId);

    @Select("select * from sys_role where identification = ${identification}")
    RoleDo selectRoleByIdentification(String identification);
}
