package org.shanzhaozhen.dynamicadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDo;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.vo.RoleVo;

import java.util.List;

public interface RoleService {

    /**
     * 通过用户id获取用户角色
     * @param userId
     * @return
     */
    List<RoleDo> getRolesByUserId(Long userId);

    /**
     * 获取角色的分页列表
     * @param baseSearchForm
     * @return
     */
    Page<RoleDo> getRolePage(BaseSearchForm baseSearchForm);

    /**
     * 通过角色id获取
     * @param roleId
     * @return
     */
    RoleDo getRoleById(Long roleId);

    /**
     * 新增角色
     * @param roleVo
     * @return
     */
    RoleDo addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     * @return
     */
    RoleDo updateRole(RoleVo roleVo);

    /**
     * 删除角色(通过角色id删除)
     * @param roleId
     * @return true 则删除成功，否则删除失败
     */
    Boolean deleteRole(Long roleId);

    /**
     * 批量插入角色-资源关系表
     * @param roleId
     * @param resourceIds
     */
    void bathAddRoleResource(Long roleId, List<Long> resourceIds);
}
