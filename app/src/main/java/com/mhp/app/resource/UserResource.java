package com.mhp.app.resource;


import com.mhp.app.manager.UserManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

    private final UserManager userManager;

    @Inject
    public UserResource(UserManager userManager) {
        this.userManager = userManager;
    }


    public Response deleteUserById(Long id) {
        userManager.deleteUserById(id);
        return Response.ok().build();
    }



}
