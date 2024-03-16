package com.mhp.app.resource;


import com.mhp.app.dto.in.WorkspaceDTO;
import com.mhp.app.entity.Workspace;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;


import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/officedesk")
@RequiredArgsConstructor
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class WorkspaceResource {





}

