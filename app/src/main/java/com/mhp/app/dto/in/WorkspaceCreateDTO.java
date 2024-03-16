package com.mhp.app.dto.in;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record WorkspaceCreateDTO(
        @Id
        String id,
        @NotBlank(message = "Desk code is mandatory")
        @Pattern(regexp = "^[A-Z]*_\\d{1,2}_[a-z]*_\\d{1,2}\\.\\d$")
        String workspaceName,
        Integer capacity
) {

}
