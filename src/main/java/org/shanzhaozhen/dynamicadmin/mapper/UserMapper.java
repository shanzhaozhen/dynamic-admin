package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.sys.UserDo;

public interface UserMapper extends BaseMapper<UserDo> {

    @Select("select * from sys_user where id = #{id}")
    UserDo selectUserByUserId(@Param("id") Long id);

    @Select("select * from sys_user where username = #{username}")
    UserDo selectUserByUsername(@Param("username") String username);

    @Select("select count(*) from sys_user where username = #{username}")
    UserDo checkUserByUsername(@Param("username") String username);

    @Select("select count(*) from sys_user where username = #{username}")
    Integer countByUsername(@Param("username") String username);
}
