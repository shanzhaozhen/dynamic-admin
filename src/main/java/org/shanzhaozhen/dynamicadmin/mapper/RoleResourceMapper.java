package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleResourceDo;

public interface RoleResourceMapper extends BaseMapper<RoleResourceDo> {

    @Delete("delete from sys_role_resource where role_id = #{roleId}")
    int deleteByRoleId(Long roleId);
}
