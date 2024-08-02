package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.teammember.control.TeamMemberService;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("workstation/team_member")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamMemberResource {

    @Inject
    TeamMemberService service;

    @POST
    @Transactional
    public Response create(TeamMember teamMember) {
        TeamMember teamMemberCreated = service.create(teamMember);
        return Response.status(Response.Status.CREATED).entity(teamMemberCreated).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        TeamMember teamMember = service.getById(id);

        if (teamMember != null) {
            return Response.ok(Response.Status.OK).entity(teamMember).build();
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
    public Response update(@PathParam("id") Long id, TeamMember teamMember) {
        TeamMember teamMemberUpdated = service.update(id, teamMember);

        if (teamMemberUpdated != null) {
            return Response.ok(Response.Status.OK).entity(teamMemberUpdated).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response get() {

        List<TeamMember> teamMembers = service.getTeamMembers();
        return Response.ok(Response.Status.OK).entity(teamMembers).build();
    }
}
