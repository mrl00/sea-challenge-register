package com.sea.challenge.register.models.dtos.security;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    @NotNull
    private String userName;

    @NotNull
    private String password;
}
