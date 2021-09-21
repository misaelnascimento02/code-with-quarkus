package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v2")
@RegisterRestClient(configKey = "countries")
@ApplicationScoped
public interface Countries {
    @GET
    @Path("/name/{region}")
    String getByRegion(@PathParam String region, @QueryParam("city") String city);

    @GET
    @Path("/name/{name}")
    String getByName(@PathParam String name);
}
