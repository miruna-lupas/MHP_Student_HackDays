package com.mhp.app.repo;

import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class WorkspaceBookingRepository implements PanacheRepositoryBase<WorkspaceBooking, Long> {

    EntityManager entityManager;

    @Inject
    public WorkspaceBookingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public WorkspaceBooking findByWorkspaceName(Long workspaceName) {
        return find("workspaceName", workspaceName).firstResult();
    }

    @Transactional
    public WorkspaceBooking createBooking(WorkspaceBooking booking) {
        persist(booking);
        return booking;
    }

    @Transactional
    public WorkspaceBooking findByUserId(Long userId) {
        return find("userId", userId).firstResult();
    }

    @Transactional
    public List<WorkspaceBooking> findAllActiveBookings() {
        return entityManager.createQuery(
                "SELECT wb FROM WorkspaceBooking wb " +
                        "WHERE wb.endDate >= :today", WorkspaceBooking.class)
        .setParameter("today", LocalDate.now())
        .getResultList();
    }

    @Transactional
    public List<WorkspaceBookingsDTO> findAllExpiredBookingsByUserId(Long userId) {
        return entityManager.createQuery(
                        "SELECT wb FROM WorkspaceBooking wb " +
                                "WHERE wb.user.id = :userId AND wb.endDate < :today", WorkspaceBooking.class)
                .setParameter("userId", userId)
                .setParameter("today", LocalDate.now())
                .getResultList()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public WorkspaceBookingsDTO convertToDTO(WorkspaceBooking booking) {
        return new WorkspaceBookingsDTO(
                booking.getWorkspace().getId(),
                booking.getStartDate().toString(),
                booking.getEndDate().toString(),
                booking.getStartTime().toString(),
                booking.getEndTime().toString()
        );
    }


    @Transactional
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
