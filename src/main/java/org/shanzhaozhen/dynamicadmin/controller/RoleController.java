package org.shanzhaozhen.dynamicadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import javax.validation.constraints.NotNull;

@Api("用户角色接口")
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
    @ApiOperation("获取角色信息（分页）")
    public ResultObject<Page<RoleVO>> getRolePage(@RequestBody BaseSearchForm baseSearchForm) {
        return ResultObject.getResultObject(result -> RoleConverter.toVO(roleService.getRolePage(baseSearchForm)));
    }

    @GetMapping(GET_ROLE_BY_ID)
    @ApiOperation("获取角色信息（通过角色id）")
    public ResultObject<RoleVO> getRoleByRoleId(@PathVariable("roleId") @ApiParam(name = "角色id", example = "1") Long roleId) {
        return ResultObject.getResultObject(result -> RoleConverter.toVO(roleService.getRoleById(roleId)));
    }

    @PostMapping(ADD_ROLE)
    @ApiOperation("添加角色接口")
    public ResultObject<RoleVO> addRole(@RequestBody @Validated({Insert.class}) RoleForm roleForm) {
        return ResultObject.getResultObject(result -> RoleConverter.toVO(roleService.addRole(RoleConverter.toDTO(roleForm))));
    }

    @PutMapping(UPDATE_ROLE)
    @ApiOperation("更新角色接口")
    public ResultObject<RoleVO> updateRole(@RequestBody @Validated({Update.class}) RoleForm roleForm) {
        return ResultObject.getResultObject(result -> RoleConverter.toVO(roleService.updateRole(RoleConverter.toDTO(roleForm))));
    }

    @DeleteMapping(DELETE_ROLE)
    @ApiOperation("删除角色接口")
    public ResultObject<Boolean> deleteRole(@PathVariable("roleId") @ApiParam(name = "角色id", example = "1") Long roleId) {
        return ResultObject.getResultObject(result -> roleService.deleteRole(roleId));
    }

}
