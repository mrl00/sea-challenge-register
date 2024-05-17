package com.sea.challenge.register.models.dtos;

import com.sea.challenge.register.models.enums.UF;

import com.sea.challenge.register.validators.CEP;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @CEP(mask = true, message = "{pattern.invalid.cep}")
    @NotNull
    private String cep;

    @NotBlank
    @Size(max = 60, message = "{address.publicplace.length}")
    @NotNull
    private String publicPlace;

    @NotBlank
    @Size(max = 100, message = "{address.neighborhood.length}")
    @NotNull
    private String neighborhood;

    @NotBlank
    @Size(max = 100, message = "{address.city.length}")
    @NotNull
    private String city;

    @NotNull
    private UF uf;

    @Size(max = 100)
    private String complement;
}
