package com.mhp.app.resource;


import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import com.mhp.app.manager.WorkspaceBookingManager;
import com.mhp.app.manager.exceptions.BookingOverlapException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkspaceBookingResource {

    @Inject
    WorkspaceBookingManager bookingManager;

    @GET
    @Path("/active")
    public List<WorkspaceBookingsDTO> getActiveBookings() {
        return bookingManager.findAllActiveBookings();
    }

    @GET
    @Path("/expired/{userId}")
    public List<WorkspaceBooking> getExpiredBookingsByUserId(@PathParam("userId") Long userId) {
        return bookingManager.getExpiredBookingsByUserId(userId);
    }


    @POST
    public WorkspaceBooking createBooking(WorkspaceBooking booking) throws BookingOverlapException {
        bookingManager.createBooking(booking);
        return booking;
    }
}