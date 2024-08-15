package com.example.springaclsecurity.config;



import com.example.springaclsecurity.model.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareProvider")
public class AuditorAwareConfiguration {

    @Bean
    public AuditorAware<User> auditorAwareProvider() {
        return new SecurityAuditorAware();
    }
}
