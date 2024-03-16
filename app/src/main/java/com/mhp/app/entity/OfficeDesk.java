package com.mhp.app.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Desk code is mandatory")
    @Pattern(regexp = "^[A-Z]*_\\d{1,2}_[a-z]*_\\d{1,2}\\.\\d$")
    private String deskCode;

    boolean isAvailableForBookings = true;
}
