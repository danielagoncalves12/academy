package com.ctw.workstation.rackasset.boundary;

import com.ctw.workstation.rackasset.control.RackAssetService;
import com.ctw.workstation.rackasset.entity.RackAsset;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("workstation/rack-assets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RackAssetResource {

    @Inject
    RackAssetService service;

    @POST
    @Transactional
    public Response create(RackAsset rackAsset) {
        try {
            RackAsset rackAssetCreated = service.create(rackAsset);
            return Response.status(Response.Status.CREATED).entity(rackAssetCreated).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        RackAsset rackAsset = service.getById(id);

        if (rackAsset != null) {
            return Response.ok(Response.Status.OK).entity(rackAsset).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean status = service.delete(id);

        if (status) {
            return Response.ok(Response.Status.OK).entity(true).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RackAsset rackAsset) {
        RackAsset rackAssetUpdated = service.update(id, rackAsset);

        if (rackAssetUpdated != null) {
            return Response.ok(Response.Status.OK).entity(rackAssetUpdated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getByStatus(@QueryParam("status") String status) {

        List<RackAsset> rackAssets = service.getRackAssets();
        return Response.ok(Response.Status.OK).entity(rackAssets).build();
    }
}
