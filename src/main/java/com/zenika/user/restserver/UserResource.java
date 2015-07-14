package com.zenika.user.restserver;

import com.zenika.user.restservice.UnkownUserException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by armel on 08/07/15.
 */
@Path("/users")
@Produces("application/json")
public interface UserResource {

    @GET
    @Path("/{id}")
    Response getUser(@PathParam("id") int id) throws UnkownUserException;

    @GET
    Response getAllUsers();
}
