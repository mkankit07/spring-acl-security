package com.example.springaclsecurity.service.impl;

import com.example.springaclsecurity.model.entities.Role;
import com.example.springaclsecurity.model.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class UserInfoDetailImpl implements UserDetails {
    @Getter
    private Integer userId;
    private String username;
    private String password;
    @Getter
    private String roles;
    @Getter
    private Integer roleId;
    private List<GrantedAuthority> authorities ;

    public UserInfoDetailImpl(final User userInfo) {

        this.userId = userInfo.getUserId();
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.authorities = userInfo.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        this.roles =  userInfo.getRoles().stream().map(Role::getName).findFirst().orElse(null);
        this.roleId =  userInfo.getRoles().stream().map(Role::getRoleId).findFirst().orElse(null);

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
