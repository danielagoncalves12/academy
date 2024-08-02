package com.ctw.workstation;

import com.ctw.workstation.it.DatabaseTestResource;
import com.ctw.workstation.it.WiremockResource;
import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.List;

public class CommonProfile implements QuarkusTestProfile {

    // Listar todas as dependências necessárias para o teste

    @Override
    public List<TestResourceEntry> testResources() {
        return List.of(
                new TestResourceEntry(DatabaseTestResource.class),
                new TestResourceEntry(WiremockResource.class)
        );
    }
}
