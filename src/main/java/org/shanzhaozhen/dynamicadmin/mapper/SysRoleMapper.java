package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.entity.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select * from sys_role r " +
            "inner join sys_role_resource srr on " +
            "r.resource_id = #{resourceId} and r.id = srr.role_id")
    List<SysResource> selectSysRoleListByResourceId(Long resourceId);


    List<SysResource> selectSysRoleListJoinResourceByType(Long resourceId);

}
