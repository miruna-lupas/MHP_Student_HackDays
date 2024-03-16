package com.mhp.app.resource;


import com.mhp.app.dto.in.WorkspaceBookingsCreateDTO;
import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import com.mhp.app.manager.WorkspaceBookingManager;
import com.mhp.app.exceptions.BookingOverlapException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
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
    public List<WorkspaceBookingsDTO> getExpiredBookingsByUserId(@PathParam("userId") Long userId) {
        return bookingManager.getExpiredBookingsByUserId(userId);
    }


    @POST
    public Response createBooking(WorkspaceBookingsCreateDTO booking) throws BookingOverlapException {
        WorkspaceBooking createdBooking = bookingManager.createBooking(booking);
        return Response.ok(createdBooking.getId()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        bookingManager.deleteBooking(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public WorkspaceBookingsDTO getBooking(@PathParam("id") Long id) {
        return bookingManager.findById(id);
    }
}