package com.mhp.app.resource;


import com.mhp.app.dto.in.WorkspaceCreateDTO;
import com.mhp.app.dto.out.WorkspaceDisplayDTO;
import com.mhp.app.exceptions.EntityAlreadyExists;
import com.mhp.app.exceptions.PayloadBuilder;
import com.mhp.app.manager.WorkspaceManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.Set;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/workspace")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class WorkspaceResource {

    private final WorkspaceManager workspaceManager;

    Validator validator;

    @Inject
    public WorkspaceResource(WorkspaceManager workspaceManager, Validator validator) {
        this.workspaceManager = workspaceManager;
        this.validator = validator;
    }


    @GET
    @Path("/{id}")
    public WorkspaceDisplayDTO getWorkspace(@PathParam("id") String id) {
        return workspaceManager.findWorkspaceByID(id);
    }

    @GET
    @Path("/{name}")
    public WorkspaceDisplayDTO getWorkspaceByName(@PathParam("name") String name) {
        return workspaceManager.findWorkspaceByName(name);
    }

    @POST
    public Response createWorkspace(@Valid WorkspaceCreateDTO workspaceCreateDTO) throws EntityAlreadyExists {
        Set<ConstraintViolation<WorkspaceCreateDTO>> violations = validator.validate(workspaceCreateDTO);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new PayloadBuilder(violations.toString()))
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity(workspaceManager.createWorkspace(workspaceCreateDTO))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteWorkspaceById(@PathParam("id") String id) {
        workspaceManager.deleteWorkspace(id);
        return Response.status(Response.Status.OK)
                .entity(new PayloadBuilder("Workspace deleted successfully"))
                .build();
    }

    @PUT
    public WorkspaceDisplayDTO updateWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        return workspaceManager.updateWorkspace(workspaceCreateDTO);
    }

    @GET
    @Path("/delete/{name}")
    public Response deleteWorkspaceByName(@PathParam("name") String name) {
        workspaceManager.deleteWorkspaceByName(name);
        return Response.status(Response.Status.OK)
                .entity(new PayloadBuilder("Workspace deleted successfully"))
                .build();
    }

}

