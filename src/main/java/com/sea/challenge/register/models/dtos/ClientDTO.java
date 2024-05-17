package com.sea.challenge.register.models.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
public class ClientDTO {
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$", message = "{client.name}")
    @JsonProperty(required = true)
    private String name;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    @CPF(message = "{client.cpf}")
    @JsonProperty(required = true)
    private String cpf;

    @NotEmpty
    @JsonProperty(required = true)
    private List<@Valid EmailDTO> emails;

    @Valid
    @JsonProperty(required = true)
    private AddressDTO address;

    @NotEmpty
    @JsonProperty(required = true)
    private List<@Valid PhoneDTO> phones;
}
