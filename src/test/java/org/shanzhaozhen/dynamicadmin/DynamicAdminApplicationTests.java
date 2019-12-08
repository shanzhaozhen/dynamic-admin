package org.shanzhaozhen.dynamicadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shanzhaozhen.dynamicadmin.common.sys.MenuType;
import org.shanzhaozhen.dynamicadmin.converter.UserDTOConverter;
import org.shanzhaozhen.dynamicadmin.dto.MenuDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.MenuDO;
import org.shanzhaozhen.dynamicadmin.mapper.MenuMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicAdminApplicationTests {

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMybatisManyToMany() {
        List<MenuDTO> list = menuMapper.getMenuRoleListByTypeAndUserId(MenuType.API.getValue(), null);
        System.out.println(list);
    }

    @Test
    public void addDefaultMenu() {
        menuMapper.insert(new MenuDO().setName("权限控制").setPath("/permission").setIcon("lock").setPriority(1));
        menuMapper.insert(new MenuDO().setName("角色切换").setPath("/page").setPid(1L).setPriority(1));
        menuMapper.insert(new MenuDO().setName("权限指令").setPath("/directive").setPid(1L).setPriority(2));
        menuMapper.insert(new MenuDO().setName("资源管理").setPath("/menu").setPid(1L).setPriority(3));
        menuMapper.insert(new MenuDO().setName("角色权限").setPath("/role").setPid(1L).setPriority(4));
    }


    @Test
    public void testConverter() {
        UserDTOConverter userDTOConverter = new UserDTOConverter();
        userDTOConverter.convert(null);
    }

}
