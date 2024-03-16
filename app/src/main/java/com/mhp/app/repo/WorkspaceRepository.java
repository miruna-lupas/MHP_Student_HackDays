package com.mhp.app.repo;

import com.mhp.app.dto.in.WorkspaceCreateDTO;
import com.mhp.app.entity.Workspace;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.Optional;


@ApplicationScoped
public class WorkspaceRepository implements PanacheRepositoryBase<Workspace, String> {

    public Optional<Workspace> findWorkspaceByID(String id) {
        return find("id", id).firstResultOptional();
    }

    public void deleteWorkspaceByID(String id) {
        delete("id", id);
    }

    public Workspace findByWorkspaceName(String name) {
        return find("name", name).firstResult();
    }

    public void deleteByWorkspaceName(String name) {
        delete("name", name);
    }

    public WorkspaceCreateDTO createWorkspace(WorkspaceCreateDTO workspace) {

        persist(new Workspace(
                        workspace.id(),
                        workspace.workspaceName(),
                        workspace.capacity()
                ));

        return workspace;
    }

    public void updateWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        var workspace = findWorkspaceByID(workspaceCreateDTO.id())
                .orElseThrow(() -> new NotFoundException("Workspace not found"));
        workspace.setWorkspaceName(workspaceCreateDTO.workspaceName());
        workspace.setCapacity(workspaceCreateDTO.capacity());
    }
}
