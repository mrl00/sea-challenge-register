package com.sea.challenge.register.models.dtos.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    @JsonProperty("username")
    @NotNull
    private String userName;

    @NotNull
    private String password;
}
