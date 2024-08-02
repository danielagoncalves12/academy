package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.control.TeamService;
import com.ctw.workstation.team.entity.TeamDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

@Path("workstation/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamService service;

    @RestClient
    private ExternalApi externalAPI;

    private static Logger logger = Logger.getLogger(TeamResource.class);

    @POST
    @Transactional
    public Response create(TeamDTO team) {
        try {
            TeamDTO teamCreated = service.create(team);

            return Response.status(Response.Status.CREATED).entity(teamCreated).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {

        // TODO - para testes
        String response1 = externalAPI.hello();
        String response2 = externalAPI.hello(new ExternalRequest("Oi"));
        System.out.println(response1);
        System.out.println(response2);

        UUID correlationId = UUID.randomUUID();
        MDC.put("requestId", correlationId.toString());

        try {
            TeamDTO team = service.getById(id);

            if (team != null) {
                return Response.ok(Response.Status.OK).entity(team).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (RuntimeException e) {
            logger.infov("Error fetching team with id: {0}", id);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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
    public Response update(@PathParam("id") Long id, TeamDTO teamDTO) {
        TeamDTO teamUpdated = service.update(id, teamDTO);

        if (teamUpdated != null) {
            return Response.ok(Response.Status.OK).entity(teamUpdated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response get(@QueryParam("name") String name) {

        try {
            // If a name is provided
            if (name != null) {
                TeamDTO team = service.getTeamByName(name);

                if (team != null) {
                    return Response.ok(Response.Status.OK).entity(team).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            } else {
                List<TeamDTO> teams = service.getTeams();
                return Response.ok(Response.Status.OK).entity(teams).build();
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}