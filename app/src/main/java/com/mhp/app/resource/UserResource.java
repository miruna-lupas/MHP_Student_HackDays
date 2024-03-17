package com.mhp.app.resource;


import com.mhp.app.dto.in.UserDTO;
import com.mhp.app.exceptions.EntityAlreadyExists;
import com.mhp.app.exceptions.PayloadBuilder;
import com.mhp.app.manager.UserManager;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@ApplicationScoped
@Path("/users")
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("ADMIN")
public class UserResource {

    Validator validator;

    private final UserManager userManager;

    @Inject
    public UserResource(UserManager userManager, Validator validator) {
        this.userManager = userManager;
        this.validator = validator;
    }

    @DELETE
    @Path("/{id}")

    public Response deleteUserById(Long id) {
        userManager.deleteUserById(id);
        return Response.status(Response.Status.OK)
                .entity(new PayloadBuilder("User deleted successfully"))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(Long id) {
        return Response.status(Response.Status.OK)
                .entity(userManager.findUserByID(id))
                .build();
    }

    @POST
    public Response createUser(@Valid UserDTO userCreateDTO) throws EntityAlreadyExists {

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userCreateDTO);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new PayloadBuilder(violations.toString()))
                    .build();
        }
        try {
            userManager.createUser(userCreateDTO);
            return Response.status(Response.Status.CREATED)
                    .entity(new PayloadBuilder("User created successfully: " + userCreateDTO))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new PayloadBuilder("Error while creating user: " + userCreateDTO))
                    .build();
        }

    }
}