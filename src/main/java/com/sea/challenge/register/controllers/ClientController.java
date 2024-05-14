package com.sea.challenge.register.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sea.challenge.register.models.dtos.ClientRequestDTO;
import com.sea.challenge.register.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/v1/client/{id}")
    public String getMethodName(@RequestParam Long id) {
        return new String();
    }

    @PostMapping("/v1/client/create")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequestDTO request) {
        return ResponseEntity.ok(request);
    }
}
