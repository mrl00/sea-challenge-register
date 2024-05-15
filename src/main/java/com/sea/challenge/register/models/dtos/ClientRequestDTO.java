package com.sea.challenge.register.models.dtos;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$", message = "{client.name}")
    @JsonProperty(required = true)
    private String name;

    @CPF(message = "{client.cpf}")
    @JsonProperty(required = true)
    private String cpf;

    @Email(message = "{client.email}")
    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private AddressDTO address;

    @JsonProperty(required = true)
    private PhoneDTO phone;
}
