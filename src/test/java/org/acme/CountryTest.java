package org.acme;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CountryTest {

    @Inject
    @RestClient
    Countries api;

    @Test
    public void testHelloEndpoint() {
        var tempo = api.getByName("France");
        System.out.println(tempo);
    }
}
