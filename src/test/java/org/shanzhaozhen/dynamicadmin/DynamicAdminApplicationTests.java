package org.shanzhaozhen.dynamicadmin;

import org.junit.jupiter.api.Test;
import org.shanzhaozhen.dynamicadmin.converter.UserDTOConverter;
import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.domain.sys.RouteDO;
import org.shanzhaozhen.dynamicadmin.mapper.RouteMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DynamicAdminApplicationTests {

    @Resource
    private RouteMapper routeMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMybatisManyToMany() {
        List<RouteDTO> list = routeMapper.getRouteRoleListByUserId(null);
        System.out.println(list);
    }

    @Test
    public void addDefaultRoute() {
        routeMapper.insert(new RouteDO().setName("权限控制").setPath("/permission").setIcon("lock").setPriority(1));
        routeMapper.insert(new RouteDO().setName("角色切换").setPath("/page").setPid(1L).setPriority(1));
        routeMapper.insert(new RouteDO().setName("权限指令").setPath("/directive").setPid(1L).setPriority(2));
        routeMapper.insert(new RouteDO().setName("资源管理").setPath("/route").setPid(1L).setPriority(3));
        routeMapper.insert(new RouteDO().setName("角色权限").setPath("/role").setPid(1L).setPriority(4));
    }


    @Test
    public void testConverter() {
        UserDTOConverter userDTOConverter = new UserDTOConverter();
        userDTOConverter.convert(null);
    }

}
