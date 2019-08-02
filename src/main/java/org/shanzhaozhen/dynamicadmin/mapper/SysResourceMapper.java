package org.shanzhaozhen.dynamicadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {

    @Select("select * from sys_resource where type = #{type}")
    List<SysResource> selectSysResourceListByType(int type);

    List<SysResource> selectSysResourceListJoinRole();

}
