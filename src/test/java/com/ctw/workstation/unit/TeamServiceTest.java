package com.ctw.workstation.unit;

import com.ctw.workstation.team.boundary.TeamResource;
import com.ctw.workstation.team.control.TeamRepository;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class TeamServiceTest {

    // Inject a mock for the real object
    @InjectMock
    private TeamRepository teamRepository;

    // Inject the real object here
    @Inject
    TeamResource teamResource;

    private Team team;

    @BeforeEach
    void setup() {
        team = new Team();
        team.setName("Team A");

        when(teamRepository.getTeamByName(anyString())).thenAnswer(inv -> {
            String input = inv.getArgument(0);
            if (input.equals("Team A")) {
                return team;
            } else {
                return null;
            }
        });
    }

    @ParameterizedTest
    @MethodSource("argumentsGetByName")
    void testGetByName(Integer expected, String name) {

        Response response = teamResource.get(name);
        Integer status = response.getStatus();

        assertEquals(expected, status);
    }

    static Stream<Arguments> argumentsGetByName() {
        return Stream.of (
                Arguments.arguments(200, "Team A"),
                Arguments.arguments(404, "Team B"),
                Arguments.arguments(404, ""),
                Arguments.arguments(404, " ")
        );
    }
}