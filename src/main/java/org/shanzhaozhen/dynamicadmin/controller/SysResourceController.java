package org.shanzhaozhen.dynamicadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @GetMapping("/resource/page")
    public Page<SysResource> getSysResourcePage() {
        return sysResourceService.getSysResourcePage();
    }

}
