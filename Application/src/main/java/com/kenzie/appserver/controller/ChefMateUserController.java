package com.kenzie.appserver.controller;


import com.kenzie.appserver.service.model.AuthUserProfile;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ChefMateUserController {
   /* @GetMapping("/profile")
    public AuthUserProfile getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        String accessToken = extractAccessToken(authorizationHeader);
        OAuth2ResourceServerProperties.Jwt jwt = decodeAccessToken(accessToken);
        String userId = jwt.;
        String email = jwt.getClaim("email").asString();
        return new AuthUserProfile(userId, email);
    }

    private String extractAccessToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }
        return authorizationHeader.substring(7);
    }

    private OAuth2ResourceServerProperties.Jwt decodeAccessToken(String accessToken) {
        String jwkSetUrl = "https://<your auth0 domain>/.well-known/jwks.json";
        JwkProvider jwkProvider = new UrlJwkProvider(jwkSetUrl);
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKeyResolver(new JwkVerificationKeyResolver(jwkProvider))
                .setExpectedIssuer("<your auth0 domain>")
                .setExpectedAudience("<your auth0 API audience>")
                .build();
        try {
            return jwtConsumer.processToClaims(accessToken).getJwt();
        } catch (InvalidJwtException e) {
            throw new IllegalArgumentException("Invalid access token", e);
        }*/

}
