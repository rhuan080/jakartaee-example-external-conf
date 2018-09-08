package com.rhuanrocha.resource;


import com.rhuanrocha.config.Config;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rhuan rocha
 */

@Path("/welcome")
public class WelcomeResource {

    @Inject
    private Config config;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response message(){

        Map<String, String> map = new HashMap<>();
        map.put("message", config.WELCOME);

        return Response
                .status( Response.Status.OK )
                .entity( map )
                .build();

    }

    @GET
    @Path("/external")
    @Produces(MediaType.APPLICATION_JSON)
    public Response messageExternalFile(){

        Map<String, String> map = new HashMap<>();
        map.put("message", config.WELCOME_EXTERNAL_FILE);

        return Response
                .status( Response.Status.OK )
                .entity( map )
                .build();

    }
}
