package com.mhp.app.dto.out;

import com.mhp.app.entity.Status;

import java.sql.Time;
import java.time.LocalDate;

public record MeetingRoomBookingsDTO(String meetingRoomName,
                                     LocalDate bookingStartDate,
                                     LocalDate bookingEndDate,
                                     Time bookingStartTime,
                                     Time bookingEndTime,
                                     Status status) {
}
