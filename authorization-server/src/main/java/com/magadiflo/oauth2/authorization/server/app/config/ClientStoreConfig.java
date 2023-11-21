package com.magadiflo.oauth2.authorization.server.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

/**
 * Client Store: Clientes registrados en el Authorization Server
 */
@Configuration
public class ClientStoreConfig {
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient sbClient = RegisteredClient.withId(UUID.randomUUID().toString()) // Id del RegisteredClient, es como una clave primaria
                .clientId("spring-boot-client") // Id que informa quién es el cliente
                .clientSecret("{noop}12345")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/spring-boot-client-idp") // Dirección de la aplicación cliente en la que recibirá el código generado para poder intercambiarlo por un token
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()) // preguntará al usuario si desea consentir a la aplicación cliente los scopes definidos
                .build();

        return new InMemoryRegisteredClientRepository(sbClient);
    }
}
