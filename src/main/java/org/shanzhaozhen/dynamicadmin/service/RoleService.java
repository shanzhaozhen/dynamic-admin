package org.shanzhaozhen.dynamicadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoleService {

    /**
     * 通过用户id获取用户角色
     * @param userId
     * @return
     */
    List<RoleDTO> getRolesByUserId(Long userId);

    /**
     * 获取角色的分页列表
     * @param baseSearchForm
     * @return
     */
    Page<RoleDTO> getRolePage(BaseSearchForm baseSearchForm);

    /**
     * 通过角色id获取
     * @param roleId
     * @return
     */
    RoleDTO getRoleById(Long roleId);

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    RoleDTO addRole(RoleDTO roleDTO);

    /**
     * 修改角色
     * @param roleDTO
     * @return
     */
    RoleDTO updateRole(RoleDTO roleDTO);

    /**
     * 删除角色(通过角色id删除)
     * @param roleId
     * @return true 则删除成功，否则删除失败
     */
    Boolean deleteRole(Long roleId);

    /**
     * 更新角色与路由和资源的关系表
     * @param roleId
     * @param routeIds
     */
    void updateRouteAndResource(@NotNull Long roleId, List<Long> routeIds, List<Long> resourceIds);

    /**
     * 批量插入角色-路由关系表
     * @param roleId
     * @param routeIds
     */
    void bathAddRoleRoute(Long roleId, List<Long> routeIds);

    /**
     * 批量插入角色-资源关系表
     * @param roleId
     * @param resourceIds
     */
    void bathAddRoleResource(Long roleId, List<Long> resourceIds);

}
