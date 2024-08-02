package com.ctw.workstation.it;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import java.util.Map;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer wiremockServer;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(3001); // No-args constructor

    @Override
    public Map<String, String> start() {

        if (wiremockServer == null) {
            wiremockServer = new WireMockServer();
            wiremockServer.start();
        }
        return Map.of("external-api.url", wiremockServer.baseUrl());
    }

    @Override
    public void stop() {
        wiremockServer.stop();
    }
}
