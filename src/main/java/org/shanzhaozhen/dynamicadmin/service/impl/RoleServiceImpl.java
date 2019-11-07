package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.shanzhaozhen.dynamicadmin.converter.RoleConverter;
import org.shanzhaozhen.dynamicadmin.dto.RoleDTO;
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
    public List<RoleDTO> getRolesByUserId(Long userId) {
        return roleMapper.getRoleByUserId(userId);
    }

    @Override
    public Page<RoleDTO> getRolePage(BaseSearchForm baseSearchForm) {
        return roleMapper.getRolePage(BaseSearchForm.getPage(baseSearchForm), baseSearchForm.getKeyword());
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {
        Assert.notNull(roleId, "获取失败：没有找到该角色");
        RoleDO roleDO = roleMapper.selectRoleByRoleId(roleId);
        Assert.notNull(roleDO, "获取失败：没有找到该角色");
        return RoleConverter.doToDTO(roleDO);
    }

    @Override
    @Transactional
    public RoleDTO addRole(RoleDTO roleDTO) {
        RoleDTO tempRoleDO = roleMapper.getRoleByIdentification(roleDTO.getIdentification());
        Assert.isNull(tempRoleDO, "创建失败：标识名称已被占用");
        RoleDO roleDO = RoleConverter.dtoToDO(roleDTO);
        roleMapper.insert(roleDO);
        if (roleDTO.getResourceIds() != null && roleDTO.getResourceIds().size() > 0) {
            assert roleDO != null;
            Long roleId = roleDO.getId();
            this.bathAddRoleResource(roleId, roleDTO.getResourceIds());
        }
        return roleDTO;
    }

    @Override
    @Transactional
    public RoleDTO updateRole(RoleDTO roleDTO) {
        RoleDTO tempRoleDO = roleMapper.getRoleByIdentification(roleDTO.getIdentification());
        Assert.isNull(tempRoleDO, "创建失败：标识名称已被占用");
        RoleDO roleDO = RoleConverter.dtoToDO(roleDTO);
        roleMapper.updateById(roleDO);
        if (roleDTO.getResourceIds() != null && roleDTO.getResourceIds().size() > 0) {
            assert roleDO != null;
            Long roleId = roleDO.getId();
            roleResourceMapper.deleteByRoleId(roleId);
            this.bathAddRoleResource(roleId, roleDTO.getResourceIds());
        }
        return roleDTO;
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
            RoleResourceDO RoleResourceDO = new RoleResourceDO(null, roleId, resourceId);
            roleResourceMapper.insert(RoleResourceDO);
        }
    }

}
