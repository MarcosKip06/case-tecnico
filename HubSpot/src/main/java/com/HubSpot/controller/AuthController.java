package com.HubSpot.controller;

import com.HubSpot.model.Token;
import com.HubSpot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authorize")
    public String authorize() {
        return authService.getAuthorizationUrl();
    }

    @GetMapping("/callback")
    public Token callback(@RequestParam("code") String code) {
        return authService.exchangeCodeForToken(code);
    }
}
