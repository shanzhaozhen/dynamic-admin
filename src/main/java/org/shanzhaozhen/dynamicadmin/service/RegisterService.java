package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.entity.sys.UserDo;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;

import java.util.Map;

public interface RegisterService {

    ResultObject registerNewUser(UserDo userDo);

    Map<String, Boolean> checkUsername(String username);

}
