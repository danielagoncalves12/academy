package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.control.BookingService;
import com.ctw.workstation.booking.entity.Booking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("workstation/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService service;

    @POST
    @Transactional
    public Response create(Booking booking) {
        Booking bookingCreated = service.create(booking);
        return Response.status(Response.Status.CREATED).entity(bookingCreated).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Booking booking = service.getById(id);

        if (booking != null) {
            return Response.ok(Response.Status.OK).entity(booking).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean status = service.delete(id);

        if (status) {
            return Response.ok(Response.Status.OK).entity(true).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Booking booking) {
        Booking bookingUpdated = service.update(id, booking);

        if (bookingUpdated != null) {
            return Response.ok(Response.Status.OK).entity(bookingUpdated).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response get() {
        List<Booking> bookings = service.getBookings();
        return Response.ok(Response.Status.OK).entity(bookings).build();
    }
}
