package com.edw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <pre>
 *     com.edw.config.SecurityConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Apr 2024 10:48
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .oauth2Client()
            .and()
                .oauth2Login()
                .tokenEndpoint()
            .and()
                .userInfoEndpoint();

        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http
                .csrf()
                    .disable()
                .authorizeHttpRequests()
                .requestMatchers("/oauth2/**", "/login/**").permitAll()
                .anyRequest()
                .fullyAuthenticated()
            .and()
                .logout()
                .logoutSuccessUrl("https://localhost:8443/auth/realms/x509-realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8081/");

        return http.build();
    }

}
