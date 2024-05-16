package com.sea.challenge.register.models.dtos.security;

import com.sea.challenge.register.models.enums.security.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private String userName;

    @NotNull(message = "password cannot be null")
    private String password;

    @NotNull(message = "role cannot be null")
    private UserRole role;
}
