package org.shanzhaozhen.dynamicadmin.service;

import org.shanzhaozhen.dynamicadmin.dto.MenuDTO;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;

import java.util.List;

public interface MenuService {

    /**
     * 通过 MenuType 类型获取所有的Menu（多对多含有角色信息）
     * @param type
     * @return
     */
    List<MenuDTO> getMenuRoleListByType(Integer type);

    /**
     * 通过当前用户的信息生成对应的前端路由
     * @return
     */
    List<AsyncRoute> getMenusByCurrentUser();

    /**
     * 获取所有资源的树形结构
     * @return
     */
    List<MenuDTO> getAllMenuTree();

    /**
     * 通过资源id获取资源实体
     * @param menuId
     * @return
     */
    MenuDTO getMenuById(Long menuId);

    /**
     * 增加资源
     * @param menuDTO
     * @return
     */
    MenuDTO addMenu(MenuDTO menuDTO);

    /**
     * 修改资源
     * @param menuDTO
     * @return
     */
    MenuDTO updateMenu(MenuDTO menuDTO);

    /**
     * 删除资源(通过资源id删除)
     * @param menuId
     * @return true 则删除成功，否则删除失败
     */
    Boolean deleteMenu(Long menuId);

}
