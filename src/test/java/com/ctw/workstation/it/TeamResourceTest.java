package com.ctw.workstation.it;

import com.ctw.workstation.CommonProfile;
import com.ctw.workstation.team.boundary.TeamResource;
import com.ctw.workstation.team.entity.TeamDTO;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestHTTPEndpoint(TeamResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestProfile(CommonProfile.class)
public class TeamResourceTest {

    private static final Jsonb JSONB = JsonbBuilder.create();
    private TeamDTO team;

    @BeforeEach
    void setup() {

        String name = "Pink";
        String product = "Car";
        String defaultLocation = "Lisbon";

        team = new TeamDTO();
        team.setName(name);
        team.setProduct(product);
        team.setDefaultLocation(defaultLocation);

        WireMock.stubFor(get(urlEqualTo("/external/hello"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("Hello from GET!")
                )
        );

        WireMock.stubFor(post(urlEqualTo("/external/hello"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("Hello from POST!")
                )
        );
    }

    @Test
    @Order(1)
    void testCreateTeamEndpoint() {

        given()
                .contentType(ContentType.JSON)
                .body(JSONB.toJson(team))
        .when()
                .post()
        .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    void testGetAllTeams() {

        List<TeamDTO> teams =
                Arrays.stream(
                        given()
                        .when()
                        .get().as(TeamDTO[].class)).toList();

        System.out.println(teams);
    }

    @Test
    @Order(3)
    void testGetByNameEndpoint() {

        given()
                .queryParam("name", team.getName())
        .when()
                .get()
        .then()
                .statusCode(200)
                .body("name", notNullValue());
                //.body("name", equalTo(team.getName()));
    }

    @Test
    @Order(4)
    void testGetTeamByIdEndpoint() {

        given()
                .pathParam("id", 1)
        .when()
                .get("/{id}")
        .then()
                .statusCode(200);
                //.body("name", equalTo(team.getName()));
    }

    @Test
    @Order(5)
    void testUpdateTeam() {

        String newName = "Super pink";
        String newProduct = "Super car";
        String newDefaultLocation = "Lisbon";

        TeamDTO updateTeam = new TeamDTO();
        updateTeam.setName(newName);
        updateTeam.setProduct(newProduct);
        updateTeam.setDefaultLocation(newDefaultLocation);

        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(JSONB.toJson(updateTeam))
        .when()
                .put("/{id}")
        .then()
                .statusCode(200);
                //.body("name", equalTo(newName))
                //.body("product", equalTo(newProduct))
                //.body("defaultLocation", equalTo(newDefaultLocation));
    }

    @Test
    @Order(6)
    void testDeleteTeam() {

        given()
                .pathParam("id", 1)
        .when()
                .delete("/{id}")
        .then()
                .statusCode(200);
    }
}
