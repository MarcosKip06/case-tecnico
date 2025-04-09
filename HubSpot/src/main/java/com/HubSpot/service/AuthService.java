package com.HubSpot.service;

import com.HubSpot.model.Token;
import com.HubSpot.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final TokenRepository tokenRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public AuthService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Value("${hubspot.scopes}")
    private String scopes;

    @Value("${hubspot.client.id}")
    private String clientId;

    @Value("${hubspot.client.secret}")
    private String clientSecret;

    @Value("${hubspot.redirect.uri}")
    private String redirectUri;

    @Value("${hubspot.token.url}")
    private String tokenUrl;

    public String getAuthorizationUrl() {
        return "https://app.hubspot.com/oauth/authorize?client_id=" + clientId +
                "&redirect_uri=" + redirectUri + "&scope=" + scopes;
    }

    public Token exchangeCodeForToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("scopes", scopes);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Token> response = restTemplate.postForEntity(tokenUrl, request, Token.class);
        Token token = response.getBody();

        if (token != null) {
            tokenRepository.save(token);
            System.out.println("AccessToken: " + token.getAccessToken());
            System.out.println("Scopes: " + this.scopes);
            System.out.println("Expires in: " + token.getExpiresIn());
        }

        return token;
    }

    public String getAccessToken() {
        Token token = tokenRepository.findTopByOrderByIdDesc();

        if (token == null) {
            throw new RuntimeException("Token não encontrado. Autentique-se primeiro.");
        }


        return token.getAccessToken();
    }



}
