package com.mhp.app.repo;

import com.mhp.app.dto.in.WorkspaceCreateDTO;
import com.mhp.app.entity.Workspace;
import com.mhp.app.exceptions.EntityAlreadyExists;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.Optional;


@ApplicationScoped
public class WorkspaceRepository implements PanacheRepositoryBase<Workspace, String> {

    @Transactional
    public Optional<Workspace> findWorkspaceByID(String id) {
        return find("id", id).firstResultOptional();
    }

    @Transactional
    public void deleteWorkspaceByID(String id) {
        delete("id", id);
    }

    @Transactional
    public Workspace findByWorkspaceName(String name) {
        return find("name", name).firstResult();
    }

    @Transactional
    public void deleteByWorkspaceName(String name) {
        delete("name", name);
    }

    @Transactional
    public WorkspaceCreateDTO createWorkspace(WorkspaceCreateDTO workspace) throws EntityAlreadyExists {

        if (findWorkspaceByID(workspace.id()).isPresent()) {
            throw new EntityAlreadyExists("Workspace with ID " + workspace.id() + " already exists");
        }
        var newWorkspace = new Workspace();
        newWorkspace.setId(workspace.id());
        newWorkspace.setWorkspaceName(workspace.workspaceName());
        newWorkspace.setCapacity(workspace.capacity());
        persist(newWorkspace);
        return workspace;

    }

    @Transactional
    public void updateWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        var workspace = findWorkspaceByID(workspaceCreateDTO.id())
                .orElseThrow(() -> new NotFoundException("Workspace not found"));
        workspace.setWorkspaceName(workspaceCreateDTO.workspaceName());
        workspace.setCapacity(workspaceCreateDTO.capacity());
    }
}
