package org.shanzhaozhen.dynamicadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.SysResourceService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @GetMapping("/resource/list")
    public ResultParam getSysResourcePage() {
        return sysResourceService.getAllSysResourceTree();
    }

}
