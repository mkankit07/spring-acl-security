package com.example.springaclsecurity.service.impl;

import com.example.springaclsecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository
            .findByUsernameIgnoreCase(username)
                .map(UserInfoDetailImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        var uds = new InMemoryUserDetailsManager();
        uds.createUser(userDetails);

        return userDetails;
    }
}
