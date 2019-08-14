package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.param.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/info")
    public ResultObject getUserInfo() {
        return ResultUtils.success(userService.getUserInfo());
    }

    @GetMapping("/user/logout")
    public ResultObject logout() {
        return ResultUtils.success();
    }

}
