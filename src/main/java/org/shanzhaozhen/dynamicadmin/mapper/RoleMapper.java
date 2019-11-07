package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDO;

import java.util.List;

public interface RoleMapper extends BaseMapper<RoleDO> {

    @Select("select r.id, r.name, r.identification, r.description, " +
            "r.createBy, r.createdDate, r.lastModifiedBy, r.lastModifiedDate " +
            "from sys_role r " +
            "inner join sys_role_resource srr on srr.resource_id = #{resourceId} and r.id = srr.role_id")
    List<RoleDO> getRoleByResourceId(@Param("resourceId") Long resourceId);

    @Select("select r.id, r.name, r.identification, r.description, " +
            "r.createBy, r.createdDate, r.lastModifiedBy, r.lastModifiedDate " +
            "from sys_role r inner join sys_user_role sur on sur.user_id = #{userId} and r.id = sur.role_id")
    List<RoleDTO> getRoleByUserId(@Param("userId") Long userId);

    @Select("select r.id, r.name, r.identification, r.description, " +
            "r.createBy, r.createdDate, r.lastModifiedBy, r.lastModifiedDate " +
            "from sys_role where name like '%${keyword}%' or identification like '%${keyword}%' or description like '%${keyword}%'")
    Page<RoleDTO> getRolePage(Page page, String keyword);

    RoleDO selectRoleByRoleId(@Param("roleId") Long roleId);

    @Select("select r.id, r.name, r.identification, r.description, " +
            "r.createBy, r.createdDate, r.lastModifiedBy, r.lastModifiedDate " +
            "from sys_role where identification = ${identification}")
    RoleDTO getRoleByIdentification(String identification);
}
