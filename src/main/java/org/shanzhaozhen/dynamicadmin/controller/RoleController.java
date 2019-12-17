package org.shanzhaozhen.dynamicadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.shanzhaozhen.dynamicadmin.converter.RoleConverter;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.form.RoleForm;
import org.shanzhaozhen.dynamicadmin.service.RoleService;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.vo.RoleVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    private final String GET_ROLE_PAGE = "/role/page";
    private final String GET_ROLE_BY_ID = "/role/{roleId}";
    private final String ADD_ROLE = "/role";
    private final String UPDATE_ROLE = "/role";
    private final String DELETE_ROLE = "/role/{roleId}";

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(GET_ROLE_PAGE)
    public ResultObject<Page<RoleVO>> getRolePage(@RequestBody BaseSearchForm baseSearchForm) {
        return ResultObject.getResultObject(result -> RoleConverter.dtoToVO(roleService.getRolePage(baseSearchForm)));
    }

    @GetMapping(GET_ROLE_BY_ID)
    public ResultObject<RoleVO> getRoleByRoleId(@PathVariable("roleId") Long roleId) {
        return ResultObject.getResultObject(result -> RoleConverter.dtoToVO(roleService.getRoleById(roleId)));
    }

    @PostMapping(ADD_ROLE)
    public ResultObject<RoleVO> addRole(@RequestBody @Validated({Insert.class}) RoleForm roleForm) {
        return ResultObject.getResultObject(result -> RoleConverter.dtoToVO(roleService.addRole(RoleConverter.formToDTO(roleForm))));
    }

    @PutMapping(UPDATE_ROLE)
    public ResultObject<RoleVO> updateRole(@RequestBody @Validated({Update.class}) RoleForm roleForm) {
        return ResultObject.getResultObject(result -> RoleConverter.dtoToVO(roleService.updateRole(RoleConverter.formToDTO(roleForm))));
    }

    @DeleteMapping(DELETE_ROLE)
    public ResultObject<Boolean> deleteRole(@PathVariable("roleId") Long roleId) {
        return ResultObject.getResultObject(result -> roleService.deleteRole(roleId));
    }

}
