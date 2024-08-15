package com.example.springaclsecurity.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    @NotBlank
    private String  username;
    @NotBlank
    private String password;
}
