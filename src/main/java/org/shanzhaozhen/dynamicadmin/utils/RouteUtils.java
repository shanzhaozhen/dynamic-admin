package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.vo.Meta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RouteUtils {

    /**
     * 将Route转换成AsyncRoute供给前端渲染使用
     * @param routeDTO
     * @return
     */
    public static AsyncRoute routeToAsyncRoute(RouteDTO routeDTO) {
        AsyncRoute asyncRoute = new AsyncRoute();
        List<RoleDTO> roleDTOList = routeDTO.getRoles();
        List<String> roles = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOList) {
            roles.add(roleDTO.getIdentification());
        }
        Meta meta = new Meta();
        meta
                .setTitle(routeDTO.getTitle())
                .setIcon(routeDTO.getIcon())
                .setNoCache(routeDTO.getNoCache())
                .setAffix(routeDTO.getAffix())
                .setBreadcrumb(routeDTO.getBreadcrumb())
                .setRoles(roles);
        asyncRoute
                .setId(routeDTO.getId())
                .setName(routeDTO.getName())
                .setPath(routeDTO.getPath())
                .setPid(routeDTO.getPid())
                .setComponent(routeDTO.getComponent())
                .setRedirect(routeDTO.getRedirect())
                .setPriority(routeDTO.getPriority())
                .setHidden(routeDTO.getHidden())
                .setAlwaysShow(routeDTO.getAlwaysShow())
                .setProps(routeDTO.getProps())
                .setMeta(meta)
                .setDescription(routeDTO.getDescription());
        return asyncRoute;
    }

    /**
     * 批量将Route转换成AsyncRoute供给前端渲染使用
     *
     * @param routeDTOList
     * @return
     */
    public static List<AsyncRoute> routeListToAsyncRouteList(List<RouteDTO> routeDTOList) {
        List<AsyncRoute> asyncRouteList = new ArrayList<>();
        for (RouteDTO routeDTO : routeDTOList) {
            AsyncRoute asyncRoute = RouteUtils.routeToAsyncRoute(routeDTO);
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
     * 将routeList生成AsyncRoute的树状结构
     * @param routeDTOList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTreeByRouteList(List<RouteDTO> routeDTOList) {
        List<AsyncRoute> asyncRouteList = RouteUtils.routeListToAsyncRouteList(routeDTOList);
        return RouteUtils.builtAsyncRouteTree(asyncRouteList);
    }

    /**
     * 装资源list转成树状结构
     * @param routeDTOList
     * @return
     */
    public static List<RouteDTO> builtRouteTree(List<RouteDTO> routeDTOList) {
        List<RouteDTO> rootList = new ArrayList<>();
        List<RouteDTO> noRootList = new ArrayList<>();

        for (RouteDTO routeDTO : routeDTOList) {
            if (routeDTO.getPid() == null || routeDTO.getPid() <= 0) {
                rootList.add(routeDTO);
            } else {
                noRootList.add(routeDTO);
            }
        }

        getRouteChildren(noRootList, routeDTOList);

        rootList.sort((Comparator.comparing(RouteDTO::getPriority)));

        return rootList;
    }

    /**
     * 对动态路由子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<RouteDTO> getRouteChildren(List<RouteDTO> noRootList, List<RouteDTO> children) {
        for (RouteDTO child : children) {
            List<RouteDTO> grandsons = new ArrayList<>();
            for (RouteDTO noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getRouteChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(RouteDTO::getPriority)));

        return children;
    }


}
