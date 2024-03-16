package com.mhp.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name must not be empty")
    @NotNull(message = "First name must not be null")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    @Email(message = "Email must be a valid email address")
    @Pattern(regexp =  "^[a-z0-9._%+-]+\\.[a-z0-9._%+-]+@nexttech\\.ro$")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must not be null")
    private Role role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfficeDeskBooking> seatbookings = new ArrayList<>();


}

