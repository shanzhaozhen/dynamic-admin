package org.shanzhaozhen.dynamicadmin.converter;

import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.form.ResourceForm;
import org.shanzhaozhen.dynamicadmin.vo.ResourceVO;
import org.springframework.beans.BeanUtils;

public class ResourceConverter {

    public static ResourceDTO formToDTO(ResourceForm resourceForm) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resourceForm, resourceDTO);
        return resourceDTO;
    }

    public static ResourceDO dtoToDO(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = new ResourceDO();
        BeanUtils.copyProperties(resourceDTO, resourceDO);
        return resourceDO;
    }

    public static ResourceDTO doToDTO(ResourceDO resourceDO) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resourceDO, resourceDTO);
        return resourceDTO;
    }

    public static ResourceVO dtoToVO(ResourceDTO resourceDTO) {
        ResourceVO resourceVO = new ResourceVO();
        BeanUtils.copyProperties(resourceDTO, resourceVO);
        return resourceVO;
    }

    public static ResourceDTO voToDTO(ResourceVO resourceVO) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resourceVO, resourceDTO);
        return resourceDTO;
    }

}
