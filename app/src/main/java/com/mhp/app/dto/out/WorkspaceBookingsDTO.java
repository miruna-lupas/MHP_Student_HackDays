package com.mhp.app.dto.out;


public record WorkspaceBookingsDTO(
        String workspaceId,
        String bookingStartDate,
        String bookingEndDate,
        String bookingStartTime,
        String bookingEndTime
) {

}
