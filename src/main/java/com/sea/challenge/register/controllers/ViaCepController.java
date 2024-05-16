package com.sea.challenge.register.controllers;

import com.sea.challenge.register.models.dtos.viacep.ViaCepDTO;
import com.sea.challenge.register.services.consumer.viacep.ViaCepConsumerService;
import com.sea.challenge.register.validators.CEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class ViaCepController {
    @Autowired
    private ViaCepConsumerService service;

    @GetMapping("/v1/cep/{cep}")
    public ResponseEntity<ViaCepDTO> getAddressViaCep(@PathVariable @CEP String cep) {
        ViaCepDTO dto = service.getCepDTO(cep);
        if(dto.notFound())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
