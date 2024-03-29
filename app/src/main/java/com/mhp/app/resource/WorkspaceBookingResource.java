package com.mhp.app.resource;


import com.mhp.app.dto.in.WorkspaceBookingsCreateDTO;
import com.mhp.app.dto.out.WorkspaceBookingsDTO;
import com.mhp.app.entity.WorkspaceBooking;
import com.mhp.app.exceptions.PayloadBuilder;
import com.mhp.app.manager.WorkspaceBookingManager;
import com.mhp.app.exceptions.BookingOverlapException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@ApplicationScoped
@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkspaceBookingResource {

    WorkspaceBookingManager bookingManager;

    @Context
    SecurityContext securityContext;


    @Inject
    public WorkspaceBookingResource(WorkspaceBookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @GET
    @Path("/active")
    @RolesAllowed("USER")
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
        return Response.status(Response.Status.CREATED)
                .entity(new PayloadBuilder("Booking created successfully" + bookingManager.findById(createdBooking.getId())))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        bookingManager.deleteBooking(id);
        return Response.status(Response.Status.OK)
                .entity(new PayloadBuilder("Booking deleted successfully"))
                .build();
    }

    @GET
    @Path("/{id}")
    public WorkspaceBookingsDTO getBooking(@PathParam("id") Long id) {
        return bookingManager.findById(id);
    }
}