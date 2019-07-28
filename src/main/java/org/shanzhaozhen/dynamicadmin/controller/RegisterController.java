package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.shanzhaozhen.dynamicadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public ResultParam register(@RequestBody SysUser sysUser) {
        return sysUserService.register(sysUser);
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ResultParam checkUserName(@PathVariable("username") String username) {
        return sysUserService.checkUsername(username);
    }

}
