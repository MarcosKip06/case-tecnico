package com.HubSpot.controller;

import com.HubSpot.dto.ContactDTO;
import com.HubSpot.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ContactDTO contactDTO) {
        System.out.println("Entrou no controller");
        return contactService.createContact(contactDTO);
    }

    @GetMapping("/teste")
    public ResponseEntity<String> testar() {
        System.out.println("⚠️ Entrou no endpoint de teste!");
        return ResponseEntity.ok("Funcionando!");
    }

}
