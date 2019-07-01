package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where id = #{id}")
    SysUser selectSysUserByUserId(String id);

    @Select("select * from sys_user where username = #{username}")
    SysUser selectSysUserByUsername(String username);

    @Select("select count(*) from sys_user where username = #{username}")
    int countByUsername(String username);
}
