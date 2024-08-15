package com.example.springaclsecurity.config;

import com.example.springaclsecurity.model.entities.User;
import com.example.springaclsecurity.service.impl.UserInfoDetailImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityAuditorAware implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(authentication -> !authentication.getPrincipal().toString().equals("anonymousUser"))
                .map(Authentication::getPrincipal)
                .map(UserInfoDetailImpl.class::cast)
                .map(userInfoDetail -> User.builder().userId(userInfoDetail.getUserId()).build());
    }
}
