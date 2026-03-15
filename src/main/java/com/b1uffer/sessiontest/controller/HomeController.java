package com.b1uffer.sessiontest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    private final OAuth2AuthorizedClientService authorizedClientService;

    public HomeController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        var authorizedClient = authorizedClientService.loadAuthorizedClient("google", authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value : " + accessToken.getTokenValue());
        System.out.println("Access Token Type : " + accessToken.getTokenType().getValue());
        System.out.println("Access Token Scope : " + accessToken.getScopes());
        System.out.println("Access Token Issue : " + accessToken.getIssuedAt());
        System.out.println("Access Token Expires : " + accessToken.getExpiresAt());


        log.info("OAuth 인증 감지");
        return "hello-oauth2";
    }
}
