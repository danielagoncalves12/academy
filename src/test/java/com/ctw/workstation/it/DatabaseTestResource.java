package com.ctw.workstation.it;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import java.util.Map;

public class DatabaseTestResource implements QuarkusTestResourceLifecycleManager {

    PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres");

    @Override
    public Map<String, String> start() {

        Log.infov("About to start test", "TestConfig.start");
        postgres.start();

        return Map.of(
            "quarkus.datasource.username", postgres.getUsername(),
            "quarkus.datasource.password", postgres.getPassword(),
            "quarkus.datasource.jdbc.url", postgres.getJdbcUrl(),
            "quarkus.log.console.json", "false"
        );
    }

    @Override
    public void stop() {
        Log.infov("Stop");
        postgres.stop();
    }
}
