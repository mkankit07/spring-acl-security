package com.example.springaclsecurity.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRoleDetails {
    private Integer userId;
    private Integer roleId;
    private String username;
    private String roleName;
    private String token;
}
