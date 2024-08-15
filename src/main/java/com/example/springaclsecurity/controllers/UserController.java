package com.example.springaclsecurity.controllers;

import com.example.springaclsecurity.model.request.LoginRequest;
import com.example.springaclsecurity.model.response.UserRoleDetails;
import com.example.springaclsecurity.service.UserService;
import com.example.springaclsecurity.utils.EntityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/login")
    EntityResponse<UserRoleDetails,Void> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(), "User login successful!", userService.authenticateUser(loginRequest));

    }

}
