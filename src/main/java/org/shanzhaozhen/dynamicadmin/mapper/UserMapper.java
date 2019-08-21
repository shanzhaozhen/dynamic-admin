package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;

public interface UserMapper extends BaseMapper<UserDO> {

    @Select("select * from sys_user where id = #{id}")
    UserDO selectUserByUserId(@Param("id") Long id);

    @Select("select * from sys_user where username = #{username}")
    UserDO selectUserByUsername(@Param("username") String username);

    @Select("select count(*) from sys_user where username = #{username}")
    UserDO checkUserByUsername(@Param("username") String username);

    @Select("select count(*) from sys_user where username = #{username}")
    Integer countByUsername(@Param("username") String username);
}
