package com.mhp.app.manager;


import com.mhp.app.dto.in.WorkspaceBookingsCreateDTO;
import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import com.mhp.app.exceptions.BookingOverlapException;
import com.mhp.app.repo.UserRepo;
import com.mhp.app.repo.WorkspaceBookingRepository;
import com.mhp.app.repo.WorkspaceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WorkspaceBookingManager {


    WorkspaceRepository workspaceRepository;

    WorkspaceBookingRepository workspaceBookingRepository;

    UserRepo userRepository;

    @Inject
    public WorkspaceBookingManager(WorkspaceBookingRepository workspaceBookingRepository, UserRepo userRepository) {
        this.workspaceBookingRepository = workspaceBookingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<WorkspaceBookingsDTO> findAllActiveBookings() {
        var bookings = workspaceBookingRepository.findAllActiveBookings();
        List<WorkspaceBookingsDTO> dtoList = new ArrayList<>();
        for (WorkspaceBooking booking : bookings) {
            dtoList.add(new WorkspaceBookingsDTO(
                    booking.getWorkspace().getId(),
                    booking.getStartDate().toString(),
                    booking.getEndDate().toString(),
                    booking.getStartTime().toString(),
                    booking.getEndTime().toString()
            ));
        }
        return dtoList;
    }


    public List<WorkspaceBookingsDTO> getExpiredBookingsByUserId(Long userId) {
        return workspaceBookingRepository.findAllExpiredBookingsByUserId(userId);
    }


    public WorkspaceBooking createBooking(WorkspaceBookingsCreateDTO createDTO) throws BookingOverlapException {
        var user = userRepository.findUserByID(createDTO.userId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        var workspace = workspaceRepository.findWorkspaceByID(createDTO.workspaceId())
                .orElseThrow(() -> new NotFoundException("Workspace not found"));

        var booking = new WorkspaceBooking();
        booking.setWorkspace(workspace);
        booking.setUser(user);
        booking.setStartDate(createDTO.bookingStartDate());
        booking.setStartTime(createDTO.bookingStartTime());
        booking.setEndDate(createDTO.bookingEndDate());
        booking.setEndTime(createDTO.bookingEndTime());
        checkForBookingOverlap(booking);

        return workspaceBookingRepository.createBooking(booking);
    }

    private boolean checkForBookingOverlap(WorkspaceBooking booking) throws BookingOverlapException {
        workspaceBookingRepository.findAllActiveBookings()
                .stream().filter(currentBooking -> !currentBooking.getId().equals(booking.getId()))
                .anyMatch(currentBooking -> currentBooking.getEndDate().isAfter(booking.getStartDate())
                        && currentBooking.getStartDate().isBefore(booking.getEndDate())
                        && currentBooking.getEndTime().isAfter(booking.getStartTime())
                        && currentBooking.getStartTime().isBefore(booking.getEndTime())
                );
        throw new BookingOverlapException("Booking overlaps with existing booking");
    }

    public WorkspaceBookingsDTO findById(Long id) {
        var booking = workspaceBookingRepository.findById(id);
        return new WorkspaceBookingsDTO(
                booking.getWorkspace().getId(),
                booking.getStartDate().toString(),
                booking.getEndDate().toString(),
                booking.getStartTime().toString(),
                booking.getEndTime().toString()
        );
    }


    public void deleteBooking(Long id) {
        workspaceBookingRepository.deleteById(id);
    }

}

