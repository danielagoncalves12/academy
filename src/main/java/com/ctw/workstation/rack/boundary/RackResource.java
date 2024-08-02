package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.control.RackService;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.entity.Team;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("workstation/racks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RackResource {

    @Inject
    RackService service;

    @POST
    @Transactional
    public Response create(Rack rack) {
        Rack rackCreated = service.create(rack);
        return Response.status(Response.Status.CREATED).entity(rackCreated).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Rack rack = service.getById(id);

        if (rack != null) {
            return Response.ok(Response.Status.OK).entity(rack).build();
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
    public Response update(@PathParam("id") Long id, Rack rack) {
        Rack rackUpdated = service.update(id, rack);

        if (rackUpdated != null) {
            return Response.ok(Response.Status.OK).entity(rackUpdated).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getByStatus(@QueryParam("status") String status) {

        List<Rack> racks;

        if (status != null) {
            racks = service.getRacksByStatus(status);
        } else {
            racks = service.getRacks();
        }

        return Response.ok(Response.Status.OK).entity(racks).build();
    }
}