package org.shanzhaozhen.dynamicadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shanzhaozhen.dynamicadmin.common.sys.ResourceType;
import org.shanzhaozhen.dynamicadmin.converter.UserDTOConverter;
import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.mapper.ResourceMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicAdminApplicationTests {

    @Resource
    private ResourceMapper resourceMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMybatisManyToMany() {
        List<ResourceDTO> list = resourceMapper.getResourceRoleListByTypeAndUserId(ResourceType.API, null);
        System.out.println(list);
    }

    @Test
    public void addDefaultResource() {
        resourceMapper.insert(new ResourceDO().setName("权限控制").setType(1).setPath("/permission").setIcon("lock").setPriority(1));
        resourceMapper.insert(new ResourceDO().setName("角色切换").setType(1).setPath("/page").setPid(1L).setPriority(1));
        resourceMapper.insert(new ResourceDO().setName("权限指令").setType(1).setPath("/directive").setPid(1L).setPriority(2));
        resourceMapper.insert(new ResourceDO().setName("资源管理").setType(1).setPath("/resource").setPid(1L).setPriority(3));
        resourceMapper.insert(new ResourceDO().setName("角色权限").setType(1).setPath("/role").setPid(1L).setPriority(4));
    }


    @Test
    public void testConverter() {
        UserDTOConverter userDTOConverter = new UserDTOConverter();
        userDTOConverter.convert(null);
    }

}
