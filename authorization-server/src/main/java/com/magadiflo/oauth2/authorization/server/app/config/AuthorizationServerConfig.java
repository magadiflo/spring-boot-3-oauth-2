package com.magadiflo.oauth2.authorization.server.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

/**
 * Authorization Server Settings: Configuración por defecto + customizaciones
 */
@Configuration
public class AuthorizationServerConfig {

    // Aplicará las configuraciones que queremos para nuestro Authorization Server
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }
}
