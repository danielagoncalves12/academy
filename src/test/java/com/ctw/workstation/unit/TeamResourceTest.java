package com.ctw.workstation.unit;

import com.ctw.workstation.team.boundary.TeamResource;
import com.ctw.workstation.team.control.TeamService;
import com.ctw.workstation.team.entity.TeamDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeamResourceTest {

    // Inject a mock for the real object
    @InjectMock
    private TeamService teamService;

    // Inject the real object here
    @Inject
    TeamResource teamResource;

    private TeamDTO team;

    @BeforeEach
    void setUp() {
        team = new TeamDTO();
        team.setName("Team A");

        when(teamService.getById(anyLong())).thenAnswer(inv -> {
            Long inputId = inv.getArgument(0);
            if (inputId > 20L) {
                return null;
            } else {
                return team;
            }
        });

        when(teamService.getTeamByName(anyString())).thenAnswer(inv -> {
            String name = inv.getArgument(0);
            if (name.equals("Team A")) {
                return team;
            } else {
                return null;
            }
        });
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("argumentsGetById")
    void testGetById(Integer expected, Long id) {

        Response response = teamResource.getById(id);
        Integer status = response.getStatus();

        assertEquals(expected, status);

        if (status == 200) {
            TeamDTO receivedTeam = (TeamDTO) response.getEntity();
            assertEquals("Team A", receivedTeam.getName());
        }
    }

    static Stream<Arguments> argumentsGetById() {
        return Stream.of (
                Arguments.arguments(200, 1L),
                Arguments.arguments(200, 2L),
                Arguments.arguments(404, 21L),
                Arguments.arguments(404, null)
        );
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("argumentsGetByName")
    void testGetByName(Integer expected, String name) {

        Response response = teamResource.get(name);
        Integer status = response.getStatus();

        assertEquals(expected, status);
    }

    static Stream<Arguments> argumentsGetByName() {
        return Stream.of (
                Arguments.arguments(200, null),
                Arguments.arguments(200, "Team A"),
                Arguments.arguments(404, "Team B"),
                Arguments.arguments(404, ""),
                Arguments.arguments(404, " ")
        );
    }
}