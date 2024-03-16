package com.mhp.app.dto.out;

public record WorkspaceBookingsDTO(
        Long bookingId,
        String workspaceId,
        String bookingStartDate,
        String bookingEndDate,
        String bookingStartTime,
        String bookingEndTime
) {
}
