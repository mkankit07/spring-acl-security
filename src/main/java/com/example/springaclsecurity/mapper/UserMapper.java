package com.example.springaclsecurity.mapper;

import com.example.springaclsecurity.model.entities.Role;
import com.example.springaclsecurity.model.entities.User;
import com.example.springaclsecurity.model.response.UserRoleDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;


@Mapper(componentModel =  "spring")
public interface UserMapper {

    @Mapping(target = "roleId", expression = "java(getUserRoleDetails(user.getRoles()).getRoleId())")
    @Mapping(target = "roleName", expression = "java(getUserRoleDetails(user.getRoles()).getName())")
    @Mapping(target = "username", source = "user.username")
    UserRoleDetails userRoleDetailsToUserRole(User user, String token);

    default Role getUserRoleDetails(Set<Role> roles) {
        return roles.stream()
                .findFirst()
                .orElse(null);
    }
}
