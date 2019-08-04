package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.entity.SysResource;
import org.shanzhaozhen.dynamicadmin.entity.SysRole;
import org.shanzhaozhen.dynamicadmin.param.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.param.Meta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResourceUtils {

    /**
     * 将SysResource转换成AsyncRoute供给前端渲染使用
     *
     * @param sysResource
     * @return
     */
    public static AsyncRoute SysResourceToAsyncRoute(SysResource sysResource) {
        AsyncRoute asyncRoute = new AsyncRoute();
        List<SysRole> roleList = sysResource.getRoles();
        List<String> roles = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            roles.add(sysRole.getRole());
        }
        Meta meta = new Meta();
        meta
                .setTitle(sysResource.getName())
                .setIcon(sysResource.getIcon())
                .setNoCache(sysResource.getNoCache())
                .setAffix(sysResource.getAffix())
                .setBreadcrumb(sysResource.getBreadcrumb())
                .setRoles(roles);
        asyncRoute
                .setId(sysResource.getId())
                .setPid(sysResource.getPid())
                .setPath(sysResource.getPath())
                .setHidden(sysResource.getHidden())
                .setAlwaysShow(sysResource.getAlwaysShow())
                .setPriority(sysResource.getPriority())
                .setMeta(meta);
        return asyncRoute;
    }

    /**
     * 批量将SysResource转换成AsyncRoute供给前端渲染使用
     *
     * @param resourceList
     * @return
     */
    public static List<AsyncRoute> SysResourceListToAsyncRouteList(List<SysResource> resourceList) {
        List<AsyncRoute> asyncRouteList = new ArrayList<>();
        for (SysResource sysResource : resourceList) {
            AsyncRoute asyncRoute = ResourceUtils.SysResourceToAsyncRoute(sysResource);
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
     * 对子节点进行递归查找
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
     * @param resourceList
     * @return
     */
    public static List<AsyncRoute> builtAsyncRouteTreeByResourceList(List<SysResource> resourceList) {
        List<AsyncRoute> asyncRouteList = ResourceUtils.SysResourceListToAsyncRouteList(resourceList);
        List<AsyncRoute> menuList = ResourceUtils.builtAsyncRouteTree(asyncRouteList);
        return menuList;
    }

}
