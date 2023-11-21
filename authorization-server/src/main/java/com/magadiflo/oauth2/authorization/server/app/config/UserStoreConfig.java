package com.magadiflo.oauth2.authorization.server.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * User Store: Usuarios registrados en Authorization Server
 */

@Configuration
public class UserStoreConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails magadiflo = User.builder()
                .username("magadiflo")
                .password("{noop}12345")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(magadiflo);
    }
}
