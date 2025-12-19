package org.nott.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Nott
 * @date 2025-12-01
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUserDetails implements UserDetails {

    /**
     * token
     */
    private String token;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userInfo.getIsLock() == null || userInfo.getIsLock() != 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userInfo.getIsLock() == null || userInfo.getIsLock() != 1;
    }
}
