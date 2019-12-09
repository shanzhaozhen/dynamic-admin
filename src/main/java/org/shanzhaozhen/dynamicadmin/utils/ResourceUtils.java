package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.dto.ResourceDTO;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.vo.Meta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResourceUtils {

    /**
     * 将Resource转换成AsyncRoute供给前端渲染使用
     * @param resourceDTO
     * @return
     */
    public static AsyncRoute resourceToAsyncRoute(ResourceDTO resourceDTO) {
        AsyncRoute asyncRoute = new AsyncRoute();
        List<RoleDTO> roleDTOList = resourceDTO.getRoles();
        List<String> roles = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOList) {
            roles.add(roleDTO.getIdentification());
        }
        Meta meta = new Meta();
        meta
                .setTitle(resourceDTO.getName())
                .setRoles(roles);
        asyncRoute
                .setId(resourceDTO.getId())
                .setPath(resourceDTO.getPath())
                .setMeta(meta);
        return asyncRoute;
    }

    /**
     * 批量将Resource转换成AsyncRoute供给前端渲染使用
     *
     * @param resourceDTOList
     * @return
     */
    public static List<AsyncRoute> resourceListToAsyncRouteList(List<ResourceDTO> resourceDTOList) {
        List<AsyncRoute> asyncRouteList = new ArrayList<>();
        for (ResourceDTO resourceDTO : resourceDTOList) {
            AsyncRoute asyncRoute = ResourceUtils.resourceToAsyncRoute(resourceDTO);
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
     * 将resourceList生成AsyncRoute的树状结构
     * @param resourceDTOList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTreeByResourceList(List<ResourceDTO> resourceDTOList) {
        List<AsyncRoute> asyncRouteList = ResourceUtils.resourceListToAsyncRouteList(resourceDTOList);
        List<AsyncRoute> routeList = ResourceUtils.builtAsyncRouteTree(asyncRouteList);
        return routeList;
    }

    /**
     * 装资源list转成树状结构
     * @param resourceDTOList
     * @return
     */
    public static List<ResourceDTO> builtResourceTree(List<ResourceDTO> resourceDTOList) {
        List<ResourceDTO> rootList = new ArrayList<>();
        List<ResourceDTO> noRootList = new ArrayList<>();

//        for (ResourceDTO resourceDTO : resourceDTOList) {
//            if (resourceDTO.getPid() == null || resourceDTO.getPid() <= 0) {
//                rootList.add(resourceDTO);
//            } else {
//                noRootList.add(resourceDTO);
//            }
//        }
//
//        getResourceChildren(noRootList, resourceDTOList);
//
//        rootList.sort((Comparator.comparing(ResourceDTO::getPriority)));

        return rootList;
    }

    /**
     * 对动态路由子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<ResourceDTO> getResourceChildren(List<ResourceDTO> noRootList, List<ResourceDTO> children) {
//        for (ResourceDTO child : children) {
//            List<ResourceDTO> grandsons = new ArrayList<>();
//            for (ResourceDTO noRoot : noRootList) {
//                if (child.getId().equals(noRoot.getPid())) {
//                    grandsons.add(noRoot);
//                }
//            }
//            if (grandsons.size() > 0) {
//                child.setChildren(getResourceChildren(noRootList, grandsons));
//            }
//        }
//        children.sort((Comparator.comparing(ResourceDTO::getPriority)));

        return children;
    }


}
