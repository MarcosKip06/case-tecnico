package com.HubSpot.service;

import com.HubSpot.dto.ContactDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContactService {

    private final AuthService authService;
    private final RestTemplate restTemplate = new RestTemplate();

    public ContactService(AuthService authService) {
        this.authService = authService;
    }

    @Value("${hubspot.api.url}")
    private String apiUrl;

    public ResponseEntity<String> createContact(ContactDTO contactDTO) {
        String url = apiUrl + "/crm/v3/objects/contacts";
        System.out.println("Entrou no método createContact");

        Map<String, Object> properties = new HashMap<>();
        properties.put("email", contactDTO.getEmail());
        properties.put("firstname", contactDTO.getFirstname());
        properties.put("lastname", contactDTO.getLastname());

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String accessToken = authService.getAccessToken();
        System.out.println("Access token usado: " + accessToken); // deve ser um UUID-like, não nulo
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);

        try {
            return restTemplate.postForEntity(url, request, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Mostra os detalhes do erro vindo do HubSpot
            System.out.println("Erro ao criar contato:");
            System.out.println("Status code: " + e.getStatusCode());
            System.out.println("Mensagem: " + e.getMessage());
            System.out.println("Resposta do HubSpot: " + e.getResponseBodyAsString());

            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }





}
