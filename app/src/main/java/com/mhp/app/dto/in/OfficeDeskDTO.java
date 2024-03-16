package com.mhp.app.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OfficeDeskDTO (
        @NotBlank(message = "Desk code is mandatory")
        @Pattern(regexp = "^[A-Z]*_\\d{1,2}_[a-z]*_\\d{1,2}\\.\\d$")
        String deskCode) {

}
