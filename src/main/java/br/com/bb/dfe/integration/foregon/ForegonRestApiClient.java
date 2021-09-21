package br.com.bb.dfe.integration.foregon;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpHeaders;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@RegisterRestClient(configKey = "foregon")
@ApplicationScoped
@RegisterProvider(ForegonRequestFilter.class)
@Path("/v1")
public interface ForegonRestApiClient {
    default String determineHeaderValue(String headerName) {
        Config config = ConfigProvider.getConfig();
        var keys = new HashMap<String, String>();
        keys.put("x-api-key", "foregon.xapikey");
        keys.put(HttpHeaders.AUTHORIZATION, "foregon.authorization");
        return config.getConfigValue(keys.get(headerName)).getValue();
    }

    default String getTokenProposal(String header) {
        var t = this.getToken("client_credentials");
        return t.getTokenType() + " " + t.getAccessToken();
    }

    default String getxapikeyProposal(String header) {
        Config config = ConfigProvider.getConfig();
        return config.getConfigValue("foregon.xapikeyproposal").getValue();
    }

    @POST
    @Path("/oauth2/token")
    @ClientHeaderParam(name = "x-api-key", value = "{determineHeaderValue}")
    @ClientHeaderParam(name = HttpHeaders.AUTHORIZATION, value = "{determineHeaderValue}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ForegonOAuthResponse getToken(@QueryParam("grant_type") String grantType);

    @POST
    @Path("/proposal")
    @ClientHeaderParam(name = HttpHeaders.AUTHORIZATION, value = "{getTokenProposal}")
    @ClientHeaderParam(name = "x-api-key", value = "{getxapikeyProposal}")
    ForegonProposalResponse sendProposal(ForegonProposal grantType) throws ForegonProposalException;
}
