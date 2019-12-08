package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleMenuDO;

public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteByRoleId(Long roleId);
}
