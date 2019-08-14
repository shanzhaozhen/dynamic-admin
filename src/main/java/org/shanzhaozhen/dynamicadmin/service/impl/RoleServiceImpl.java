package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDo;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.entity.sys.RoleResourceDo;
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
    public List<RoleDo> getRolesByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public Page<RoleDo> getRolePage(BaseSearchForm baseSearchForm) {
        return roleMapper.selectRolePage(BaseSearchForm.getPage(baseSearchForm), baseSearchForm.getKeyword());
    }

    @Override
    public RoleDo getRoleById(Long roleId) {
        Assert.notNull(roleId, "获取失败：没有找到该角色");
        RoleDo roleDo = roleMapper.selectRoleByRoleId(roleId);
        Assert.notNull(roleDo, "获取失败：没有找到该角色");
        return roleDo;
    }

    @Override
    @Transactional
    public RoleDo addRole(RoleDo roleDo) {
        RoleDo tempRoleDo = roleMapper.selectRoleByIdentification(roleDo.getIdentification());
        Assert.isNull(tempRoleDo, "创建失败：标识名称已被占用");
        roleMapper.insert(roleDo);
        if (roleDo.getResourceIds() != null && roleDo.getResourceIds().size() > 0) {
            Long roleId = roleDo.getId();
            this.bathAddRoleResource(roleId, roleDo.getResourceIds());
        }
        return roleDo;
    }

    @Override
    @Transactional
    public RoleDo updateRole(RoleDo roleDo) {
        Assert.notNull(roleDo.getId(), "更新失败：没有找到对应的角色");
        RoleDo tempRoleDo = roleMapper.selectRoleByIdentification(roleDo.getIdentification());
        Assert.isNull(tempRoleDo, "创建失败：标识名称已被占用");
        roleMapper.updateById(roleDo);
        if (roleDo.getResourceIds() != null && roleDo.getResourceIds().size() > 0) {
            Long roleId = roleDo.getId();
            roleResourceMapper.deleteByRoleId(roleId);
            this.bathAddRoleResource(roleId, roleDo.getResourceIds());
        }
        return roleDo;
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
            RoleResourceDo RoleResourceDo = new RoleResourceDo(roleId, resourceId);
            roleResourceMapper.insert(RoleResourceDo);
        }
    }

}
