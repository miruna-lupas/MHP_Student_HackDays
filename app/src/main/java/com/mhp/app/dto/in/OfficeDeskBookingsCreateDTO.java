package com.mhp.app.dto.in;

import java.sql.Time;
import java.time.LocalDate;

public record OfficeDeskBookingsCreateDTO(
        String workspaceId,
        Long userId,
        LocalDate bookingStartDate,
        Time bookingStartTime,
        LocalDate bookingEndDate,
        Time bookingEndTime) {
}
