package com.ctw.workstation;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "Rack Resource", description = "Rack operations"),
                @Tag(name = "Booking Resource", description = "Booking operations"),
                @Tag(name = "Team Resource", description = "Team operations")
        },
        info = @Info(
                title = "Rack Booking API",
                version = "1.0")
)

public class RackApplication extends Application { }