package com.mhp.app.repo;

import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class WorkspaceBookingRepository implements PanacheRepositoryBase<WorkspaceBooking, Long> {

    public WorkspaceBooking findByWorkspaceName(Long workspaceName) {
        return find("workspaceName", workspaceName).firstResult();
    }

    public WorkspaceBooking createBooking(WorkspaceBooking booking) {
        persist(booking);
        return booking;
    }

    public WorkspaceBooking findByUserId(Long userId) {
        return find("userId", userId).firstResult();
    }

    public List<WorkspaceBooking> findAllActiveBookings() {
        return list("endDate >= :today", Parameters.with("today", LocalDate.now()));
    }

   public List<WorkspaceBookingsDTO> findAllExpiredBookingsByUserId(Long userId) {
        return list("userId = :userId and endDate < :today", Parameters.with("userId", userId).and("today", LocalDate.now()))
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private WorkspaceBookingsDTO convertToDTO(WorkspaceBooking booking) {
        return new WorkspaceBookingsDTO(
                booking.getWorkspace().getId(),
                booking.getStartDate().toString(),
                booking.getEndDate().toString(),
                booking.getStartTime().toString(),
                booking.getEndTime().toString()
        );
    }


    public List<WorkspaceBooking> findConflictingBookings(String workspaceId,
                                                          LocalDate startDate,
                                                          LocalTime startTime,
                                                          LocalDate endDate,
                                                          LocalTime endTime) {
        return find("workspace.id = ?1 and ((startDate < ?3 and endDate > ?2) or " +
                        "(startDate = ?3 and startTime < ?5) or " +
                        "(endDate = ?2 and endTime > ?4) or " +
                        "(startDate = ?3 and endDate = ?2 and startTime < ?5 and endTime > ?4))",
                workspaceId, startDate, endDate, startTime, endTime).list();
    }
}
