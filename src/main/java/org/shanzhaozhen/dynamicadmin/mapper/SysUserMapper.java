package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysUser;

import java.io.Serializable;

public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectSysUserByUserId(String userId);

    @Select("select * from sys_user where username = #{userName}")
    SysUser selectSysUserByUsername(String username);
}
