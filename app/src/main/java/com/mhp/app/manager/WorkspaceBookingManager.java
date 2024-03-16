package com.mhp.app.manager;


import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import com.mhp.app.manager.exceptions.BookingOverlapException;
import com.mhp.app.repo.WorkspaceBookingRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.list;

@ApplicationScoped
public class WorkspaceBookingManager {


    WorkspaceBookingRepository bookingRepository;

    @Inject
    public WorkspaceBookingManager(WorkspaceBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public List<WorkspaceBookingsDTO> findAllActiveBookings() {
        var today = LocalDate.now();
        List<WorkspaceBooking> bookings = bookingRepository.list("endDate >= ?1", today);
        List<WorkspaceBookingsDTO> dtos = new ArrayList<>();
        for (WorkspaceBooking booking : bookings) {
            dtos.add(new WorkspaceBookingsDTO(
                    booking.getId(),
                    booking.getWorkspace().getId(),
                    booking.getStartDate().toString(),
                    booking.getEndDate().toString(),
                    booking.getStartTime().toString(),
                    booking.getEndTime().toString()
            ));
        }
        return dtos;
    }


    public List<WorkspaceBooking> getExpiredBookingsByUserId(Long userId) {
        return bookingRepository.findAllExpiredBookingsByUserId(userId);
    }

    public void createBooking(WorkspaceBooking booking) throws BookingOverlapException {
        if (!isWorkspaceAvailable(booking)) {
            throw new BookingOverlapException("Workspace is not available during the specified timeframe");
        }
        bookingRepository.persist(booking);
    }

    private boolean isWorkspaceAvailable(WorkspaceBooking booking) {
        var startDate = booking.getStartDate();
        var startTime = booking.getStartTime();
        var endDate = booking.getEndDate();
        var endTime = booking.getEndTime();
        List<WorkspaceBooking> conflictingBookings = bookingRepository.findConflictingBookings(
                booking.getWorkspace().getId(), startDate, startTime, endDate, endTime);
        return conflictingBookings.isEmpty();
    }

}

