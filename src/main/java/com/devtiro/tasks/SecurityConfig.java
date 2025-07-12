package com.devtiro.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Enable CORS (uses WebConfig.java settings)
                .cors(Customizer.withDefaults())
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Permit OPTIONS requests for CORS preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Permit all API endpoints (adjust if authentication is needed)
                        .requestMatchers("/api/**").permitAll()
                        // Require authentication for any other requests
                        .anyRequest().authenticated()
                )
                // Disable CSRF for stateless REST API
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}