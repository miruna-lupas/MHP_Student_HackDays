package com.mhp.app.dto.out;

import com.mhp.app.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UserDisplayDTO (String email, @Enumerated(EnumType.STRING) Role role) {

}
