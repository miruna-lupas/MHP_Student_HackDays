package com.mhp.app.dto.out;

public record MeetingRoomDTO (String meetingRoomName, Integer capacity, boolean isAvailableForBookings) {
}