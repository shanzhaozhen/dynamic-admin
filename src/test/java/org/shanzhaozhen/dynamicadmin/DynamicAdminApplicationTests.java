package org.shanzhaozhen.dynamicadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.mapper.SysResourceMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicAdminApplicationTests {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMybatisManyToMany() {
        List<SysResource> list = sysResourceMapper.selectSysResourceListJoinRole();
        System.out.println(list);
    }


}
