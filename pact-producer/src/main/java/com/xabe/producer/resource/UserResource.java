package com.xabe.producer.resource;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/user-service/user")
public class UserResource {


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
        return new User(id, "firstName", "lastName");
    }

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return "OK";
    }
}
