package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDo;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDo;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.param.Meta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResourceUtils {

    /**
     * 将Resource转换成AsyncRoute供给前端渲染使用
     * @param resourceDo
     * @return
     */
    public static AsyncRoute resourceToAsyncRoute(ResourceDo resourceDo) {
        AsyncRoute asyncRoute = new AsyncRoute();
        List<RoleDo> roleDoList = resourceDo.getRoleDos();
        List<String> roles = new ArrayList<>();
        for (RoleDo roleDo : roleDoList) {
            roles.add(roleDo.getIdentification());
        }
        Meta meta = new Meta();
        meta
                .setTitle(resourceDo.getName())
                .setIcon(resourceDo.getIcon())
                .setNoCache(resourceDo.getNoCache())
                .setAffix(resourceDo.getAffix())
                .setBreadcrumb(resourceDo.getBreadcrumb())
                .setRoles(roles);
        asyncRoute
                .setId(resourceDo.getId())
                .setPid(resourceDo.getPid())
                .setPath(resourceDo.getPath())
                .setHidden(resourceDo.getHidden())
                .setAlwaysShow(resourceDo.getAlwaysShow())
                .setPriority(resourceDo.getPriority())
                .setMeta(meta);
        return asyncRoute;
    }

    /**
     * 批量将Resource转换成AsyncRoute供给前端渲染使用
     *
     * @param resourceDoList
     * @return
     */
    public static List<AsyncRoute> resourceListToAsyncRouteList(List<ResourceDo> resourceDoList) {
        List<AsyncRoute> asyncRouteList = new ArrayList<>();
        for (ResourceDo resourceDo : resourceDoList) {
            AsyncRoute asyncRoute = ResourceUtils.resourceToAsyncRoute(resourceDo);
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
     * @param resourceDoList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTreeByResourceList(List<ResourceDo> resourceDoList) {
        List<AsyncRoute> asyncRouteList = ResourceUtils.resourceListToAsyncRouteList(resourceDoList);
        List<AsyncRoute> menuList = ResourceUtils.builtAsyncRouteTree(asyncRouteList);
        return menuList;
    }

    /**
     * 装资源list转成树状结构
     * @param resourceDoList
     * @return
     */
    public static List<ResourceDo> builtResourceTree(List<ResourceDo> resourceDoList) {
        List<ResourceDo> rootList = new ArrayList<>();
        List<ResourceDo> noRootList = new ArrayList<>();

        for (ResourceDo resourceDo : resourceDoList) {
            if (resourceDo.getPid() == null || resourceDo.getPid() <= 0) {
                rootList.add(resourceDo);
            } else {
                noRootList.add(resourceDo);
            }
        }

        getResourceChildren(noRootList, resourceDoList);

        rootList.sort((Comparator.comparing(ResourceDo::getPriority)));

        return rootList;
    }

    /**
     * 对动态路由子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<ResourceDo> getResourceChildren(List<ResourceDo> noRootList, List<ResourceDo> children) {
        for (ResourceDo child : children) {
            List<ResourceDo> grandsons = new ArrayList<>();
            for (ResourceDo noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getResourceChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(ResourceDo::getPriority)));

        return children;
    }


}
