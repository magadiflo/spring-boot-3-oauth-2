package com.magadiflo.oauth2.client.app.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {

    private final WebClient webClient;

    public ClientController(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://127.0.0.1:9090").build();
    }

    @GetMapping(path = "/home")
    public Mono<String> home(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client, @AuthenticationPrincipal OidcUser oidcUser) {
        return Mono.just("""
                <h4>Access Token: %s</h4>
                <h4>Refresh Token: %s</h4>
                <h4>Id Token: %s</h4>
                <h4>Claims: %s</h4>
                """.formatted(
                client.getAccessToken().getTokenValue(),  // Pertenece al cliente
                client.getRefreshToken().getTokenValue(),       // Pertenece al cliente
                oidcUser.getIdToken().getTokenValue(),          // Pertenece al usuario logueado
                oidcUser.getClaims()));                         // Pertenece al usuario logueado
    }

    @GetMapping(path = "/tasks")
    public Mono<String> getTasks(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return this.webClient.get()
                .uri("tasks")
                .header("Authorization", "Bearer %s".formatted(client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class);
    }
}
