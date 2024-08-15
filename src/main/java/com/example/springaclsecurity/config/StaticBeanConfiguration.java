package com.example.springaclsecurity.config;


import com.example.springaclsecurity.utils.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StaticBeanConfiguration {

    @Value("${jwt.secret.key}")
    String jwtSecretKey;

    @Value("${jwt.expiry.time}")
    Long jwtTokenExpiryTimeInMillis;

    @PostConstruct
    public void init() {
        JwtUtils.setJwtSecretKey(jwtSecretKey);
        JwtUtils.setJwtTokenExpiryTimeInMillis(jwtTokenExpiryTimeInMillis);
    }

}
