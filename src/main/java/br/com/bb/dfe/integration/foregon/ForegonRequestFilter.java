package br.com.bb.dfe.integration.foregon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import org.apache.http.util.EntityUtils;
import java.io.InputStreamReader;


import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class ForegonRequestFilter implements ContainerRequestFilter, ClientRequestFilter, ContainerResponseFilter,
        ClientResponseFilter, ResponseExceptionMapper {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        System.out.println("\n\nRequest");
        System.out.println("######"+requestContext.getUri().toString());        
        System.out.println(requestContext.getStringHeaders());            
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {      
        // requestContext.getEntityStream()
        // System.out.println(requestContext.getHeaderString("Authorization"));
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        // System.out.println(requestContext.getHeaderString("Authorization"));
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public Throwable toThrowable(Response response) {

        String content = response.readEntity(String.class);
        System.out.println(content);

        var error =  response.readEntity(ForegonProposalResponseError.class);
        return new ForegonProposalException(error);
    }

}
