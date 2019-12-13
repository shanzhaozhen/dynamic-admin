package org.shanzhaozhen.dynamicadmin.converter;

import com.baomidou.mybatisplus.extension.api.R;
import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RouteDO;
import org.shanzhaozhen.dynamicadmin.form.RouteForm;
import org.shanzhaozhen.dynamicadmin.vo.RouteVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RouteConverter {

    public static RouteDTO formToDTO(RouteForm routeForm) {
        RouteDTO routeDTO = new RouteDTO();
        BeanUtils.copyProperties(routeForm, routeDTO);
        return routeDTO;
    }

    public static RouteDO dtoToDO(RouteDTO routeDTO) {
        RouteDO routeDO = new RouteDO();
        BeanUtils.copyProperties(routeDTO, routeDO);
        return routeDO;
    }

    public static RouteDTO doToDTO(RouteDO routeDO) {
        RouteDTO routeDTO = new RouteDTO();
        BeanUtils.copyProperties(routeDO, routeDTO);
        return routeDTO;
    }

    public static RouteVO dtoToVO(RouteDTO routeDTO) {
        RouteVO routeVO = new RouteVO();
        BeanUtils.copyProperties(routeDTO, routeVO);
        return routeVO;
    }

    public static RouteDTO voToDTO(RouteVO routeVO) {
        RouteDTO routeDTO = new RouteDTO();
        BeanUtils.copyProperties(routeVO, routeDTO);
        return routeDTO;
    }

    public static List<RouteVO> dtoToVO(List<RouteDTO> routeDTOTree) {
        List<RouteVO> routeVOTree = new ArrayList<>();

        for (RouteDTO routeDTO : routeDTOTree) {
            RouteVO routeVO = RouteConverter.dtoToVO(routeDTO);
            if (routeDTO.getChildren() != null && routeDTO.getChildren().size() > 0) {
                routeVO.setChildren(RouteConverter.dtoToVO(routeDTO.getChildren()));
            }
            routeVOTree.add(routeVO);
        }

        return routeVOTree;
    }
}
