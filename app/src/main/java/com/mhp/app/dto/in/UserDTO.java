package com.mhp.app.dto.in;

import com.mhp.app.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotBlank(message = "Email must not be empty")
        @NotNull(message = "Email must not be null")
        @Email(message = "Email must be a valid email address")
         String email,

                @NotBlank(message = "Password must not be empty")
        @NotNull(message = "Password must not be null")
        @Size(min = 8, message = "Password must be at least 8 characters")
         String password,

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Role must not be null")
         Role role
) {

}
