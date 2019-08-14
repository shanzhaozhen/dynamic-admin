package org.shanzhaozhen.dynamicadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shanzhaozhen.dynamicadmin.common.ResourceType;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDo;
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
        List<ResourceDo> list = resourceMapper.selectResourceRoleListByTypeAndUserId(ResourceType.API, null);
        System.out.println(list);
    }

    @Test
    public void addDefaultResource() {
        resourceMapper.insert(new ResourceDo().setName("权限控制").setType(1).setPath("/permission").setIcon("lock").setPriority(1));
        resourceMapper.insert(new ResourceDo().setName("角色切换").setType(1).setPath("/page").setPid(1L).setPriority(1));
        resourceMapper.insert(new ResourceDo().setName("权限指令").setType(1).setPath("/directive").setPid(1L).setPriority(2));
        resourceMapper.insert(new ResourceDo().setName("资源管理").setType(1).setPath("/resource").setPid(1L).setPriority(3));
        resourceMapper.insert(new ResourceDo().setName("角色权限").setType(1).setPath("/role").setPid(1L).setPriority(4));
    }


}
