package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDO;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleResourceDO;
import org.shanzhaozhen.dynamicadmin.mapper.RoleMapper;
import org.shanzhaozhen.dynamicadmin.mapper.RoleResourceMapper;
import org.shanzhaozhen.dynamicadmin.service.RoleService;
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
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<RoleDO> getRolesByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public Page<RoleDO> getRolePage(BaseSearchForm baseSearchForm) {
        return roleMapper.selectRolePage(BaseSearchForm.getPage(baseSearchForm), baseSearchForm.getKeyword());
    }

    @Override
    public RoleDO getRoleById(Long roleId) {
        Assert.notNull(roleId, "获取失败：没有找到该角色");
        RoleDO roleDO = roleMapper.selectRoleByRoleId(roleId);
        Assert.notNull(roleDO, "获取失败：没有找到该角色");
        return roleDO;
    }

    @Override
    @Transactional
    public RoleDO addRole(RoleDO roleDO) {
        RoleDO tempRoleDO = roleMapper.selectRoleByIdentification(roleDO.getIdentification());
        Assert.isNull(tempRoleDO, "创建失败：标识名称已被占用");
        roleMapper.insert(roleDO);
        if (roleDO.getResourceIds() != null && roleDO.getResourceIds().size() > 0) {
            Long roleId = roleDO.getId();
            this.bathAddRoleResource(roleId, roleDO.getResourceIds());
        }
        return roleDO;
    }

    @Override
    @Transactional
    public RoleDO updateRole(RoleDO roleDO) {
        Assert.notNull(roleDO.getId(), "更新失败：没有找到对应的角色");
        RoleDO tempRoleDO = roleMapper.selectRoleByIdentification(roleDO.getIdentification());
        Assert.isNull(tempRoleDO, "创建失败：标识名称已被占用");
        roleMapper.updateById(roleDO);
        if (roleDO.getResourceIds() != null && roleDO.getResourceIds().size() > 0) {
            Long roleId = roleDO.getId();
            roleResourceMapper.deleteByRoleId(roleId);
            this.bathAddRoleResource(roleId, roleDO.getResourceIds());
        }
        return roleDO;
    }

    @Override
    @Transactional
    public Boolean deleteRole(Long roleId) {
        int count = roleMapper.deleteById(roleId);
        return count > 0;
    }

    @Override
    @Transactional
    public void bathAddRoleResource(Long roleId, List<Long> resourceIds) {
        for (Long resourceId : resourceIds) {
            RoleResourceDO RoleResourceDO = new RoleResourceDO(roleId, resourceId);
            roleResourceMapper.insert(RoleResourceDO);
        }
    }

}
