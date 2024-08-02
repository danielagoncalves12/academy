package com.ctw.workstation.team.boundary;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/external/hello")
@RegisterRestClient(configKey = "external-api")
public interface ExternalApi {

    @GET
    String hello();

    @POST
    String hello(ExternalRequest externalRequest);
}
