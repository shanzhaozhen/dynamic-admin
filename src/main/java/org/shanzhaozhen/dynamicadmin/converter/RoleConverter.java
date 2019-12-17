package org.shanzhaozhen.dynamicadmin.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.domain.sys.RoleDO;
import org.shanzhaozhen.dynamicadmin.form.RoleForm;
import org.shanzhaozhen.dynamicadmin.vo.RoleVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {

    public static RoleDTO formToDTO(RoleForm roleForm) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(roleForm, roleDTO);
        return roleDTO;
    }

    public static RoleDO dtoToDO(RoleDTO roleDTO) {
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(roleDTO, roleDO);
        return roleDO;
    }

    public static RoleDTO doToDTO(RoleDO roleDO) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(roleDO, roleDTO);
        return roleDTO;
    }

    public static RoleVO dtoToVO(RoleDTO roleDTO) {
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(roleDTO, roleVO);
        List<RouteDTO> resourceDTOList = roleDTO.getResourceDTOList();
        if (resourceDTOList != null && resourceDTOList.size() > 0) {
            List<Long> resourceIds = new ArrayList<>();
            for (RouteDTO resourceDTO : resourceDTOList) {
                resourceIds.add(resourceDTO.getId());
            }
            roleVO.setResourceIds(resourceIds);
        }
        return roleVO;
    }

    public static RoleDTO voToDTO(RoleVO roleVO) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(roleVO, roleDTO);
        return roleDTO;
    }

    public static Page<RoleVO> dtoToVO(Page<RoleDTO> rolePage) {
        return null;
    }
}
