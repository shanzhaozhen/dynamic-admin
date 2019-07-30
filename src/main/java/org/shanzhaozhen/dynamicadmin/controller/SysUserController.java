package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/info")
    public ResultParam getUserInfo() {
        return sysUserService.getUserInfo();
    }

}
