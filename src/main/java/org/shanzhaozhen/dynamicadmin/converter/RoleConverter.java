package org.shanzhaozhen.dynamicadmin.converter;

import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDO;
import org.shanzhaozhen.dynamicadmin.form.RoleForm;
import org.shanzhaozhen.dynamicadmin.vo.RoleVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {

    public static RoleDTO formToDTO(RoleForm roleForm) {
        return null;
    }

    public static RoleDO dtoToDO(RoleDTO roleDTO) {
        return null;
    }

    public static RoleDTO doToDTO(RoleDO roleDO) {
        return null;
    }

    public static RoleVO dtoToVO(RoleDTO roleDTO) {
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(roleDTO, roleVO);
        List<ResourceDTO> resourceDTOList = roleDTO.getResourceDTOList();
        if (resourceDTOList != null && resourceDTOList.size() > 0) {
            List<Long> resourceIds = new ArrayList<>();
            for (ResourceDTO resourceDTO : resourceDTOList) {
                resourceIds.add(resourceDTO.getId());
            }
            roleVO.setResourceIds(resourceIds);
        }
        return roleVO;
    }

    public static RoleDTO voToDTO(RoleVO roleVO) {
        return null;
    }

}
