package com.mhp.app.entity;


import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceBooking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonbDateFormat("dd-MM-yyyy")
    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @JsonbDateFormat("dd-MM-yyyy")
    @NotNull(message = "End date is mandatory")
    private LocalDate endDate;

    @NotNull(message = "Start time is mandatory")
    private LocalTime  startTime;

    @NotNull(message = "End time is mandatory")
    private LocalTime endTime;


    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
