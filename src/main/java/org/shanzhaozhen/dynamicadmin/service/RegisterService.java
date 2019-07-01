package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;

import java.util.Map;

public interface RegisterService {

    ResultParam RegisterNewUser(SysUser sysUser);

    Map<String, Boolean> checkUsername(String username);

}
