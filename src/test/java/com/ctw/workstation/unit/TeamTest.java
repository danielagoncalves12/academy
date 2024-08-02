package com.ctw.workstation.unit;

import com.ctw.workstation.CommonProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CommonProfile.class)
public class TeamTest {

    @ParameterizedTest
    @MethodSource("teamArguments")
    void team(Integer expected, String name) throws IOException {
        // Given or arrange
        final StringBuilder request = new StringBuilder("http://localhost:8080/workstation/teams?");
        request.append("name=").append(name);

        HttpUriRequest UriRequest = new HttpGet(request.toString());

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(UriRequest);

        // Assert
        Integer status = httpResponse.getStatusLine().getStatusCode();
        assertEquals(expected, status, "Validate if gets the right team");
    }
    
    static Stream<Arguments> teamArguments() {
        return Stream.of (
            Arguments.arguments(404, "Stars")
        );
    }
}
