package com.sea.challenge.register.controllers;

import com.sea.challenge.register.models.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sea.challenge.register.models.dtos.ClientRequestDTO;
import com.sea.challenge.register.services.ClientService;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/v1/client/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {
        Optional<ClientRequestDTO> clientDTO = clientService.findClientById(id);
        if(clientDTO.isPresent())
            return ResponseEntity.ok(clientDTO.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/v1/client")
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequestDTO request) {
        Client savedClient = clientService.saveClient(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.getClientId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
