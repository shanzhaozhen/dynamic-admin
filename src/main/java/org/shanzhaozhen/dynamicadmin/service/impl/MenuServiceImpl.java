package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.shanzhaozhen.dynamicadmin.common.sys.ResourceType;
import org.shanzhaozhen.dynamicadmin.converter.MenuConverter;
import org.shanzhaozhen.dynamicadmin.dto.MenuDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.MenuDO;
import org.shanzhaozhen.dynamicadmin.mapper.MenuMapper;
import org.shanzhaozhen.dynamicadmin.utils.MyBeanUtils;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.service.MenuService;
import org.shanzhaozhen.dynamicadmin.utils.MenuUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuDTO> getMenuRoleListByType(Integer type) {
        return menuMapper.getMenuRoleListByTypeAndUserId(type, null);
    }

    @Override
    public List<AsyncRoute> getMenusByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        List<MenuDTO> menuDTOList = menuMapper.getMenuRoleListByTypeAndUserId(ResourceType.MENU.getValue(), userId);
        return MenuUtils.builtAsyncRouteTreeByMenuList(menuDTOList);
    }

    @Override
    public List<MenuDTO> getAllMenuTree() {
        List<MenuDTO> menuDTOList = menuMapper.getMenuList();
        return MenuUtils.builtMenuTree(menuDTOList);
    }

    @Override
    public MenuDTO getMenuById(Long menuId) {
        MenuDO menuDO = menuMapper.selectById(menuId);
        Assert.notNull(menuDO, "获取失败：没有找到该资源或已被删除");
        return MenuConverter.doToDTO(menuDO);
    }

    @Override
    @Transactional
    public MenuDTO addMenu(MenuDTO menuDTO) {
        MenuDO menuDO = MenuConverter.dtoToDO(menuDTO);
        menuMapper.insert(menuDO);
        return menuDTO;
    }

    @Override
    @Transactional
    public MenuDTO updateMenu(MenuDTO menuDTO) {
        Assert.notNull(menuDTO.getId(), "资源id不能为空");
        MenuDO menuDO = menuMapper.selectById(menuDTO.getId());
        Assert.notNull(menuDO, "更新失败：没有找到该资源或已被删除");
        MyBeanUtils.copyPropertiesExcludeMeta(menuDTO, menuDO);
        menuMapper.updateById(menuDO);
        return menuDTO;
    }

    @Override
    @Transactional
    public Boolean deleteMenu(Long menuId) {
        return SqlHelper.retBool(menuMapper.deleteById(menuId));
    }

}
