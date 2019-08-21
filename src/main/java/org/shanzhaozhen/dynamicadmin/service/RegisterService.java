package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDO;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;

import java.util.Map;

public interface RegisterService {

    ResultObject registerNewUser(UserDO userDO);

    Map<String, Boolean> checkUsername(String username);

}
