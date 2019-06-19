package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 3064727069207896868L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private Set<GrantedAuthority> authorities;

    private boolean accountNonExpired;          //账户是否过期,过期无法验证

    private boolean accountNonLocked;           //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证

    private boolean credentialsNonExpired;      //指示是否已过期的用户的凭据(密码),过期的凭据防止认证

    private boolean enabled;                    //是否被禁用,禁用的用户不能身份验证

    private List<SysRole> sysRoles;

    private String nickname;

    private String fullName;

    private Integer sex;

    private Date birthday;

    private String headImg;

    private String email;

    private String phoneNumber;

    private String address;

    private String introduction;

}
