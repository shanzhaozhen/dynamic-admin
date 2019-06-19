package org.shanzhaozhen.dynamicadmin.utils;

import com.alibaba.druid.util.StringUtils;
import org.shanzhaozhen.dynamicadmin.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsUtils {

    /**
     * 获取当前登录用户授权.
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 获取当前登录用户名.
     */
    public static String getUsername() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(username) || StringUtils.isEmpty(username)) {
            return null;
        }
        return username;
    }

    /**
     * 获取当前登录用户账号.
     */
    public static String getSysUsername() {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "";
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    /**
     * 获取当前登录用户ID.
     */
    public static Long getSysUserId() {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return null;
        }
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysUser.getId();
    }

    /**
     * 获取当前登录用户实体.
     */
    public static SysUser getSysUser() {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return null;
        }
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysUser;
    }


    /**
     * 获取当前登录用户已有的权限.
     */
    public static List<String> getActionsByLoginUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> resLst = new ArrayList<>();
        for (GrantedAuthority granted : userDetails.getAuthorities()) {
            resLst.add(granted.getAuthority());
        }
        return resLst;
    }

    /**
     * 全局获取HttpServletResponse对象
     */
    public static HttpServletResponse getHttpServletResponse() {
        HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 全局获取HttpServletRequest对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }



}
