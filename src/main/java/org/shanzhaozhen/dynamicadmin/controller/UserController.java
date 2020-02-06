package org.shanzhaozhen.dynamicadmin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.vo.UserInfo;
import org.springframework.web.bind.annotation.*;

@Api("用户信息接口")
@RestController
public class UserController {

    private final String GET_USER_INFO = "/user/info";
    private final String LOGOUT = "/user/logout";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(GET_USER_INFO)
    @ApiOperation("获取当前登录用户信息接口")
    public ResultObject<UserInfo> getUserInfo() {
        return ResultObject.getResultObject(result -> userService.getUserInfo());
    }

    @GetMapping(LOGOUT)
    @ApiOperation("登出用户接口")
    public ResultObject<Boolean> logout() {
        return ResultObject.getResultObject(result -> true);
    }

}
