package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDO;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.param.Meta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResourceUtils {

    /**
     * 将Resource转换成AsyncRoute供给前端渲染使用
     * @param resourceDO
     * @return
     */
    public static AsyncRoute resourceToAsyncRoute(ResourceDO resourceDO) {
        AsyncRoute asyncRoute = new AsyncRoute();
        List<RoleDO> roleDOList = resourceDO.getRoleDOs();
        List<String> roles = new ArrayList<>();
        for (RoleDO roleDO : roleDOList) {
            roles.add(roleDO.getIdentification());
        }
        Meta meta = new Meta();
        meta
                .setTitle(resourceDO.getName())
                .setIcon(resourceDO.getIcon())
                .setNoCache(resourceDO.getNoCache())
                .setAffix(resourceDO.getAffix())
                .setBreadcrumb(resourceDO.getBreadcrumb())
                .setRoles(roles);
        asyncRoute
                .setId(resourceDO.getId())
                .setPid(resourceDO.getPid())
                .setPath(resourceDO.getPath())
                .setHidden(resourceDO.getHidden())
                .setAlwaysShow(resourceDO.getAlwaysShow())
                .setPriority(resourceDO.getPriority())
                .setMeta(meta);
        return asyncRoute;
    }

    /**
     * 批量将Resource转换成AsyncRoute供给前端渲染使用
     *
     * @param resourceDOList
     * @return
     */
    public static List<AsyncRoute> resourceListToAsyncRouteList(List<ResourceDO> resourceDOList) {
        List<AsyncRoute> asyncRouteList = new ArrayList<>();
        for (ResourceDO resourceDO : resourceDOList) {
            AsyncRoute asyncRoute = ResourceUtils.resourceToAsyncRoute(resourceDO);
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
     * @param resourceDOList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTreeByResourceList(List<ResourceDO> resourceDOList) {
        List<AsyncRoute> asyncRouteList = ResourceUtils.resourceListToAsyncRouteList(resourceDOList);
        List<AsyncRoute> menuList = ResourceUtils.builtAsyncRouteTree(asyncRouteList);
        return menuList;
    }

    /**
     * 装资源list转成树状结构
     * @param resourceDOList
     * @return
     */
    public static List<ResourceDO> builtResourceTree(List<ResourceDO> resourceDOList) {
        List<ResourceDO> rootList = new ArrayList<>();
        List<ResourceDO> noRootList = new ArrayList<>();

        for (ResourceDO resourceDO : resourceDOList) {
            if (resourceDO.getPid() == null || resourceDO.getPid() <= 0) {
                rootList.add(resourceDO);
            } else {
                noRootList.add(resourceDO);
            }
        }

        getResourceChildren(noRootList, resourceDOList);

        rootList.sort((Comparator.comparing(ResourceDO::getPriority)));

        return rootList;
    }

    /**
     * 对动态路由子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<ResourceDO> getResourceChildren(List<ResourceDO> noRootList, List<ResourceDO> children) {
        for (ResourceDO child : children) {
            List<ResourceDO> grandsons = new ArrayList<>();
            for (ResourceDO noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getResourceChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(ResourceDO::getPriority)));

        return children;
    }


}
