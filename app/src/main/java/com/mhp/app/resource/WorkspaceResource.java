package com.mhp.app.resource;


import com.mhp.app.dto.in.WorkspaceCreateDTO;
import com.mhp.app.dto.out.WorkspaceDisplayDTO;
import com.mhp.app.manager.WorkspaceManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.RequiredArgsConstructor;


import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/workspace")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class WorkspaceResource {

    private final WorkspaceManager workspaceManager;

    @Inject
    public WorkspaceResource(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
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
    public WorkspaceDisplayDTO createWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        return workspaceManager.createWorkspace(workspaceCreateDTO);
    }

    @DELETE
    @Path("/{id}")
    public void deleteWorkspaceById(@PathParam("id") String id) {
        workspaceManager.deleteWorkspace(id);
    }

    @PUT
    public WorkspaceDisplayDTO updateWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        return workspaceManager.updateWorkspace(workspaceCreateDTO);
    }

    @GET
    @Path("/delete/{name}")
    public void deleteWorkspaceByName(@PathParam("name") String name) {
        workspaceManager.deleteWorkspaceByName(name);
    }

}

