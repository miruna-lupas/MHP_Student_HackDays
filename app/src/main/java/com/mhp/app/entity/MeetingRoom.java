package com.mhp.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowPrintStacktrace;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllowPrintStacktrace
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[a-zA-Z0-9\\s]*$")
    @NotBlank(message = "Meeting room name is mandatory")
    private String meetingRoomName;


    @NotNull(message = "Capacity is mandatory")
    private Integer capacity;

    boolean isAvailableForBookings = true;


}
