package org.shanzhaozhen.dynamicadmin.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.shanzhaozhen.dynamicadmin.converter.RouteConverter;
import org.shanzhaozhen.dynamicadmin.dto.RouteDTO;
import org.shanzhaozhen.dynamicadmin.entity.sys.RouteDO;
import org.shanzhaozhen.dynamicadmin.mapper.RouteMapper;
import org.shanzhaozhen.dynamicadmin.utils.MyBeanUtils;
import org.shanzhaozhen.dynamicadmin.vo.AsyncRoute;
import org.shanzhaozhen.dynamicadmin.service.RouteService;
import org.shanzhaozhen.dynamicadmin.utils.RouteUtils;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Resource
    private RouteMapper routeMapper;

    @Override
    public List<RouteDTO> getRouteRoleListByType(Integer type) {
        return routeMapper.getRouteRoleListByUserId(null);
    }

    @Override
    public List<AsyncRoute> getRoutesByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        Assert.notNull(userId, "没有获取到当前的登录状态或为匿名用户");
        List<RouteDTO> routeDTOList = routeMapper.getRouteRoleListByUserId(userId);
        return RouteUtils.builtAsyncRouteTreeByRouteList(routeDTOList);
    }

    @Override
    public List<RouteDTO> getAllRouteTree() {
        List<RouteDTO> routeDTOList = routeMapper.getRouteList();
        return RouteUtils.builtRouteTree(routeDTOList);
    }

    @Override
    public RouteDTO getRouteById(Long routeId) {
        RouteDO routeDO = routeMapper.selectById(routeId);
        Assert.notNull(routeDO, "获取失败：没有找到该菜单或已被删除");
        return RouteConverter.doToDTO(routeDO);
    }

    @Override
    @Transactional
    public RouteDTO addRoute(RouteDTO routeDTO) {
        RouteDO routeDO = RouteConverter.dtoToDO(routeDTO);
        routeMapper.insert(routeDO);
        return routeDTO;
    }

    @Override
    @Transactional
    public RouteDTO updateRoute(RouteDTO routeDTO) {
        Assert.notNull(routeDTO.getId(), "菜单id不能为空");
        RouteDO routeDO = routeMapper.selectById(routeDTO.getId());
        Assert.notNull(routeDO, "更新失败：没有找到该菜单或已被删除");
        MyBeanUtils.copyPropertiesExcludeMeta(routeDTO, routeDO);
        routeMapper.updateById(routeDO);
        return routeDTO;
    }

    @Override
    @Transactional
    public Boolean deleteRoute(Long routeId) {
        return SqlHelper.retBool(routeMapper.deleteById(routeId));
    }

}
