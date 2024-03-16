package com.mhp.app.dto.out;

public record OfficeDeskBookingsDTO(
        String deskCode,
        String bookingStartDate,
        String bookingEndDate,
        String bookingStartTime,
        String bookingEndTime
) {
}
