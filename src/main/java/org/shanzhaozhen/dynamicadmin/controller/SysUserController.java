package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.service.RegisterService;
import org.shanzhaozhen.dynamicadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    public Map<String, Object> register(SysUser sysUser) {
        return sysUserService.register(sysUser);
    }

    @GetMapping("/check/{username}")
    @ResponseBody
    public Map<String, Boolean> checkUserName(@PathVariable("username") String username) {
        return sysUserService.checkUsername(username);
    }

}
