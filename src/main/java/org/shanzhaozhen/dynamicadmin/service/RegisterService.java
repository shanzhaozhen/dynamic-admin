package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.dto.UserDTO;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;

import java.util.Map;

public interface RegisterService {

    ResultObject registerNewUser(UserDTO userDTO);

    Map<String, Boolean> checkUsername(String username);

}
