package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.UserService;
import org.shanzhaozhen.dynamicadmin.vo.UserInfo;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final String GET_USER_INFO = "/user/info";
    private final String LOGOUT = "/user/logout";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(GET_USER_INFO)
    public ResultObject<UserInfo> getUserInfo() {
        return ResultObject.getResultObject(result -> userService.getUserInfo());
    }

    @GetMapping(LOGOUT)
    public ResultObject<Boolean> logout() {
        return ResultObject.getResultObject(result -> true);
    }

}
