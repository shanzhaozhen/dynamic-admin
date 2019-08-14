package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDo;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService sysUserService;

    @PostMapping
    public ResultObject register(@RequestBody UserDo userDo) {
        sysUserService.register(userDo);
        return ResultUtils.success("注册成功");
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ResultObject checkUserName(@PathVariable("username") String username) {
        return sysUserService.isExistUser(username) ? ResultUtils.success() : ResultUtils.failure();
    }

}
