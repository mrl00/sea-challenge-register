package com.sea.challenge.register.models.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$", message = "invalid name")
    private String name;

    @CPF(message = "invalid CPF")
    private String cpf;

    @Email(message = "invalid email")
    private String email;
}
