package com.nus.sgevent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // For demonstration and testing, disable CSRF. Not recommended for production
            .authorizeRequests(authz -> authz
                .requestMatchers("/users/register", "/users/login").permitAll() // Allow everyone to access registration and login
                .anyRequest().authenticated() // All other requests need authentication
            )
            .formLogin(form -> form
                .loginProcessingUrl("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
            );
        return http.build();
    }
}
