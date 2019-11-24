package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.UserConverter;
import org.shanzhaozhen.dynamicadmin.form.UserForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService sysUserService;

    @PostMapping
    public ResultObject register(@RequestBody @Validated UserForm userForm) {
        return ResultUtils.success(sysUserService.register(UserConverter.formToDTO(userForm)));
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ResultObject checkUserName(@PathVariable("username") String username) {
        return ResultUtils.defaultResult(sysUserService.isExistUser(username));
    }

}
