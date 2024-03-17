package com.mhp.app.dto.in;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkspaceBookingsCreateDTO(
        String workspaceId,
        Long userId,
        @NotNull(message = "Start date is mandatory")
        @JsonbDateFormat("dd-MM-yyyy")
        LocalDate bookingStartDate,
        @NotNull(message = "Start time is mandatory")
        LocalTime bookingStartTime,
        @NotNull(message = "End date is mandatory")
        @JsonbDateFormat("dd-MM-yyyy")
        LocalDate bookingEndDate,
        @NotNull(message = "End time is mandatory")
        LocalTime bookingEndTime) {
}
