package org.shanzhaozhen.dynamicadmin.converter;

import org.shanzhaozhen.dynamicadmin.dto.MenuDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.MenuDO;
import org.shanzhaozhen.dynamicadmin.form.MenuForm;
import org.shanzhaozhen.dynamicadmin.vo.MenuVO;
import org.springframework.beans.BeanUtils;

public class MenuConverter {

    public static MenuDTO formToDTO(MenuForm menuForm) {
        MenuDTO menuDTO = new MenuDTO();
        BeanUtils.copyProperties(menuForm, menuDTO);
        return menuDTO;
    }

    public static MenuDO dtoToDO(MenuDTO menuDTO) {
        MenuDO menuDO = new MenuDO();
        BeanUtils.copyProperties(menuDTO, menuDO);
        return menuDO;
    }

    public static MenuDTO doToDTO(MenuDO menuDO) {
        MenuDTO menuDTO = new MenuDTO();
        BeanUtils.copyProperties(menuDO, menuDTO);
        return menuDTO;
    }

    public static MenuVO dtoToVO(MenuDTO menuDTO) {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menuDTO, menuVO);
        return menuVO;
    }

    public static MenuDTO voToDTO(MenuVO menuVO) {
        MenuDTO menuDTO = new MenuDTO();
        BeanUtils.copyProperties(menuVO, menuDTO);
        return menuDTO;
    }

}
