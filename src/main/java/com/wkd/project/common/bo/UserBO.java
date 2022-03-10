package com.wkd.project.common.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * @author wkd
 * @version 1.0.0
 * @className UserBO.java
 * @description TODO
 * @createTime 2021-11-04 10:39
 */
@Data
public class UserBO implements Serializable, UserDetails {

    private static final long serialVersionUID = -7063346437183681145L;

    private Long id;

    private Long deptId;

    private String username;

    private String nickName;

    private String userType;

    private String email;

    private String phone;

    private String sex;

    private String avatar;

    private String password;

    private String status;

    private String loginIp;

    private LocalDateTime loginDate;

    private String createBy;

    private LocalDateTime createDate;

    private String updateBy;

    private LocalDateTime updateDate;

    private Boolean deleteFlag;

    private String remark;

    private String roleName;

    private Collection<GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isEnabled() {
        return deleteFlag;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
}