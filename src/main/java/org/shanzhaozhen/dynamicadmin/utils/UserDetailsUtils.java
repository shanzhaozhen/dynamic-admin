package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.param.JWTUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserDetailsUtils {

    /**
     * 获取当前登录用户授权.
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户名JWTUser.
     */
    public static JWTUser getJWTUser() {
        return (JWTUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户名.
     */
    public static Long getUserId() {
        JWTUser jwtUser = (JWTUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUser.getId();
    }

    /**
     * 获取当前登录用户名.
     */
    public static String getUsername() {
        JWTUser jwtUser = (JWTUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUser.getUsername();
    }

    /**
     * 获取当前登录用户已有的权限.
     */
    public static List<String> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> resLst = new ArrayList<>();
        for (GrantedAuthority granted : authentication.getAuthorities()) {
            resLst.add(granted.getAuthority());
        }
        return resLst;
    }

    /**
     * 全局获取HttpServletResponse对象
     */
    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletWebRequest) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * 全局获取HttpServletRequest对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }



}
