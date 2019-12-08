package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.dto.MenuDTO;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.vo.Meta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuUtils {

    /**
     * 将Menu转换成AsyncRoute供给前端渲染使用
     * @param menuDTO
     * @return
     */
    public static AsyncRoute menuToAsyncRoute(MenuDTO menuDTO) {
        AsyncRoute asyncRoute = new AsyncRoute();
        List<RoleDTO> roleDTOList = menuDTO.getRoles();
        List<String> roles = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOList) {
            roles.add(roleDTO.getIdentification());
        }
        Meta meta = new Meta();
        meta
                .setTitle(menuDTO.getName())
                .setIcon(menuDTO.getIcon())
                .setNoCache(menuDTO.getNoCache())
                .setAffix(menuDTO.getAffix())
                .setBreadcrumb(menuDTO.getBreadcrumb())
                .setRoles(roles);
        asyncRoute
                .setId(menuDTO.getId())
                .setPid(menuDTO.getPid())
                .setPath(menuDTO.getPath())
                .setHidden(menuDTO.getHidden())
                .setAlwaysShow(menuDTO.getAlwaysShow())
                .setPriority(menuDTO.getPriority())
                .setMeta(meta);
        return asyncRoute;
    }

    /**
     * 批量将Menu转换成AsyncRoute供给前端渲染使用
     *
     * @param menuDTOList
     * @return
     */
    public static List<AsyncRoute> menuListToAsyncRouteList(List<MenuDTO> menuDTOList) {
        List<AsyncRoute> asyncRouteList = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOList) {
            AsyncRoute asyncRoute = MenuUtils.menuToAsyncRoute(menuDTO);
            asyncRouteList.add(asyncRoute);
        }
        return asyncRouteList;
    }

    /**
     * 将asyncRouteList生成树状结构
     * @param asyncRouteList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTree(List<AsyncRoute> asyncRouteList) {
        List<AsyncRoute> rootList = new ArrayList<>();
        List<AsyncRoute> noRootList = new ArrayList<>();

        for (AsyncRoute asyncRoute : asyncRouteList) {
            if (asyncRoute.getPid() == null || asyncRoute.getPid() <= 0) {
                rootList.add(asyncRoute);
            } else {
                noRootList.add(asyncRoute);
            }
        }

        getAsyncRouteChildren(noRootList, asyncRouteList);

        rootList.sort((Comparator.comparing(AsyncRoute::getPriority)));

        return rootList;
    }

    /**
     * 对动态路由子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<AsyncRoute> getAsyncRouteChildren(List<AsyncRoute> noRootList, List<AsyncRoute> children) {
        for (AsyncRoute child : children) {
            List<AsyncRoute> grandsons = new ArrayList<>();
            for (AsyncRoute noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getAsyncRouteChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(AsyncRoute::getPriority)));

        return children;
    }

    /**
     * 将menuList生成AsyncRoute的树状结构
     * @param menuDTOList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTreeByMenuList(List<MenuDTO> menuDTOList) {
        List<AsyncRoute> asyncRouteList = MenuUtils.menuListToAsyncRouteList(menuDTOList);
        List<AsyncRoute> menuList = MenuUtils.builtAsyncRouteTree(asyncRouteList);
        return menuList;
    }

    /**
     * 装资源list转成树状结构
     * @param menuDTOList
     * @return
     */
    public static List<MenuDTO> builtMenuTree(List<MenuDTO> menuDTOList) {
        List<MenuDTO> rootList = new ArrayList<>();
        List<MenuDTO> noRootList = new ArrayList<>();

        for (MenuDTO menuDTO : menuDTOList) {
            if (menuDTO.getPid() == null || menuDTO.getPid() <= 0) {
                rootList.add(menuDTO);
            } else {
                noRootList.add(menuDTO);
            }
        }

        getMenuChildren(noRootList, menuDTOList);

        rootList.sort((Comparator.comparing(MenuDTO::getPriority)));

        return rootList;
    }

    /**
     * 对动态路由子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<MenuDTO> getMenuChildren(List<MenuDTO> noRootList, List<MenuDTO> children) {
        for (MenuDTO child : children) {
            List<MenuDTO> grandsons = new ArrayList<>();
            for (MenuDTO noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getMenuChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(MenuDTO::getPriority)));

        return children;
    }


}
