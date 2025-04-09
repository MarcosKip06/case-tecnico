package com.HubSpot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @PostMapping("/contact-creation")
    public ResponseEntity<Void> receiveWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Webhook recebido: " + payload);
        return ResponseEntity.ok().build();
    }
}
