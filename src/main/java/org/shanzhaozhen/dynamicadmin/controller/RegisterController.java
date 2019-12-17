package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.UserConverter;
import org.shanzhaozhen.dynamicadmin.form.UserForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final String REGISTER = "/register";
    private final String CHECK_USERNAME = "/register/{username}";

    private final UserService sysUserService;

    public RegisterController(UserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping(REGISTER)
    public ResultObject<Long> register(@RequestBody @Validated UserForm userForm) {
        return ResultObject.getResultObject(result -> sysUserService.register(UserConverter.formToDTO(userForm)));
    }

    @GetMapping(CHECK_USERNAME)
    @ResponseBody
    public ResultObject<Boolean> checkUserName(@PathVariable("username") String username) {
        return ResultObject.getResultObject(result -> sysUserService.isExistUser(username));
    }

}
