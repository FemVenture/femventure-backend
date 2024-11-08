package com.femventure.Security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class that contains the configuration for the security in the backend application
 * @author Grupo1*/
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    AuthenticationManager authenticationManager;

    public SecurityConfig(
    ) {
    }

    /**
     * Bean that manages the security configuration
     * @param http Object HttpSecurity
     * @return Object SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) -> {
            log.error("[!] UNAUTHORIZED ERROR -> {}", authException.getMessage());
            response.sendError(401, authException.getMessage());
        }));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize -> {
            //todas estas rutas se permiten (no requieren autenticación)
            authorize.requestMatchers(
                    "/swagger-ui/**",
                    "/api/v1/auth/**",
                    "/v3/api-docs/**",
                    "/error",
                    "/favicon.ico",
                    "/api/v1/**"
            ).permitAll();

            //todas las demás rutas requieren autenticación
            authorize.anyRequest().authenticated();
        });

        return http.build();
    }

}
