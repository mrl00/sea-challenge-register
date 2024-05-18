package com.sea.challenge.register.models.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$", message = "{pattern.invalid.name}")
    @NotNull
    private String name;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{pattern.invalid.cpf}")
    @CPF(message = "{client.cpf}")
    @NotNull
    private String cpf;

    @NotEmpty(message = "{client.emails.nonempty}")
    @NotNull(message = "{client.emails.nonnull}")
    private List<@Valid EmailDTO> emails;

    @Valid
    @NotNull(message = "{client.address.nonnull}")
    private AddressDTO address;

    @NotEmpty(message = "{client.phones.nonempty}")
    @NotNull(message = "{client.phones.nonnull}")
    private List<@Valid PhoneDTO> phones;
}
