package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.bb.dfe.integration.foregon.ForegonOAuthResponse;
import br.com.bb.dfe.integration.foregon.ForegonRestApiClient;

@Path("/hello")
public class GreetingResource {

    
    @Inject
    @RestClient
    ForegonRestApiClient api;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        ForegonOAuthResponse xpto = api.getToken(null);
         return xpto.toString();
    }
}