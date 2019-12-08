package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.shanzhaozhen.dynamicadmin.converter.RoleConverter;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDO;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleMenuDO;
import org.shanzhaozhen.dynamicadmin.mapper.RoleMapper;
import org.shanzhaozhen.dynamicadmin.mapper.RoleMenuMapper;
import org.shanzhaozhen.dynamicadmin.service.RoleService;
import org.shanzhaozhen.dynamicadmin.utils.MyBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleDTO> getRolesByUserId(Long userId) {
        return roleMapper.getRoleListByUserId(userId);
    }

    @Override
    public Page<RoleDTO> getRolePage(BaseSearchForm baseSearchForm) {
        return roleMapper.getRolePage(BaseSearchForm.getPage(baseSearchForm), baseSearchForm.getKeyword());
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {
        RoleDTO roleDTO = roleMapper.getRoleByRoleId(roleId);
        Assert.notNull(roleDTO, "获取失败：没有找到该角色或已被删除");
        return roleDTO;
    }

    @Override
    @Transactional
    public RoleDTO addRole(RoleDTO roleDTO) {
        RoleDTO roleInDB = roleMapper.getRoleByIdentification(roleDTO.getIdentification());
        Assert.isNull(roleInDB, "创建失败：标识名称已被占用");
        RoleDO roleDO = RoleConverter.dtoToDO(roleDTO);
        roleMapper.insert(roleDO);
        if (roleDTO.getResourceIds() != null && roleDTO.getResourceIds().size() > 0) {
            Long roleId = roleDO.getId();
            this.bathAddRoleResource(roleId, roleDTO.getResourceIds());
        }
        return roleDTO;
    }

    @Override
    @Transactional
    public RoleDTO updateRole(RoleDTO roleDTO) {
        Assert.notNull(roleDTO.getId(), "角色id不能为空");
        RoleDTO roleInDB = roleMapper.getRoleByIdNotInAndIdentification(roleDTO.getId(), roleDTO.getIdentification());
        Assert.isNull(roleInDB, "更新失败：标识名称已被占用");
        RoleDO roleDO = roleMapper.selectById(roleDTO.getId());
        Assert.notNull(roleDO, "更新失败：没有找到该角色或已被删除");
        MyBeanUtils.copyPropertiesExcludeMeta(roleDTO, roleDO);
        roleMapper.updateById(roleDO);
        if (roleDTO.getResourceIds() != null && roleDTO.getResourceIds().size() > 0) {
            Long roleId = roleDO.getId();
            roleMenuMapper.deleteByRoleId(roleId);
            this.bathAddRoleResource(roleId, roleDTO.getResourceIds());
        }
        return roleDTO;
    }

    @Override
    @Transactional
    public Boolean deleteRole(Long roleId) {
        roleMenuMapper.deleteByRoleId(roleId);
        return SqlHelper.retBool(roleMapper.deleteById(roleId));
    }

    @Override
    @Transactional
    public void bathAddRoleResource(Long roleId, List<Long> resourceIds) {
        for (Long resourceId : resourceIds) {
            RoleMenuDO RoleMenuDO = new RoleMenuDO(null, roleId, resourceId);
            roleMenuMapper.insert(RoleMenuDO);
        }
    }

}
