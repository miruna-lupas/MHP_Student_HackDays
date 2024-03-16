package com.mhp.app.dto.in;

import java.sql.Time;
import java.time.LocalDate;

public record MeetingRoomBookingsDTO(Long meetingRoomId,
                                     Long userId,
                                     LocalDate bookingStartDate,
                                     LocalDate bookingEndDate,
                                     Time bookingStartTime,
                                     Time bookingEndTime) {
}
