package com.example.springaclsecurity.service;

import com.example.springaclsecurity.exceptions.DataNotFoundException;
import com.example.springaclsecurity.mapper.UserMapper;
import com.example.springaclsecurity.model.entities.User;
import com.example.springaclsecurity.model.request.LoginRequest;
import com.example.springaclsecurity.model.response.UserRoleDetails;
import com.example.springaclsecurity.repositories.RoleRepository;
import com.example.springaclsecurity.repositories.UserRepository;
import com.example.springaclsecurity.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserRoleDetails authenticateUser(final LoginRequest loginRequest) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        final User user = userRepository
                .findByUsernameIgnoreCase(loginRequest.getUsername())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        final String accessToken = JwtUtils.generateToken(loginRequest);
        return userMapper.userRoleDetailsToUserRole(user, accessToken);
    }

}
