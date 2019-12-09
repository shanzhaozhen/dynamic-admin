package org.shanzhaozhen.dynamicadmin.converter;

import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RouteDO;
import org.shanzhaozhen.dynamicadmin.form.RouteForm;
import org.shanzhaozhen.dynamicadmin.vo.RouteVO;
import org.springframework.beans.BeanUtils;

public class RouteConverter {

    public static RouteDTO formToDTO(RouteForm menuForm) {
        RouteDTO menuDTO = new RouteDTO();
        BeanUtils.copyProperties(menuForm, menuDTO);
        return menuDTO;
    }

    public static RouteDO dtoToDO(RouteDTO menuDTO) {
        RouteDO menuDO = new RouteDO();
        BeanUtils.copyProperties(menuDTO, menuDO);
        return menuDO;
    }

    public static RouteDTO doToDTO(RouteDO menuDO) {
        RouteDTO menuDTO = new RouteDTO();
        BeanUtils.copyProperties(menuDO, menuDTO);
        return menuDTO;
    }

    public static RouteVO dtoToVO(RouteDTO menuDTO) {
        RouteVO menuVO = new RouteVO();
        BeanUtils.copyProperties(menuDTO, menuVO);
        return menuVO;
    }

    public static RouteDTO voToDTO(RouteVO menuVO) {
        RouteDTO menuDTO = new RouteDTO();
        BeanUtils.copyProperties(menuVO, menuDTO);
        return menuDTO;
    }

}
