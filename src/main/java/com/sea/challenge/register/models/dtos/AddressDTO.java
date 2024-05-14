package com.sea.challenge.register.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sea.challenge.register.models.UF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @Pattern(regexp = "^[0-9]{2}\\.[0-9]{3}-[0-9]{3}$", message = "{address.cep}")
    @JsonProperty(required = true)
    private String cep;

    @NotBlank
    @Size(max = 60, message = "{address.publicplace}")
    @JsonProperty(required = true)
    private String publicPlace;

    @NotBlank
    @Size(max = 100, message = "{address.neighborhood}")
    @JsonProperty(required = true)
    private String neighborhood;

    @NotBlank
    @Size(max = 100, message = "{address.city}")
    @JsonProperty(required = true)
    private String city;

    @JsonProperty(required = true)
    private UF uf;

    @Size(max = 100)
    private String complemento;
}
