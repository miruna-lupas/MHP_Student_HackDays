package com.mhp.app.manager;

import com.mhp.app.dto.in.WorkspaceCreateDTO;
import com.mhp.app.dto.out.WorkspaceDisplayDTO;
import com.mhp.app.exceptions.EntityAlreadyExists;
import com.mhp.app.repo.WorkspaceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class WorkspaceManager {

    private final WorkspaceRepository workspaceRepository;

    @Inject
    public WorkspaceManager(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }


    public WorkspaceDisplayDTO createWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        workspaceRepository.findWorkspaceByID(workspaceCreateDTO.id())
                .ifPresent(workspace -> {
                    try {
                        throw new EntityAlreadyExists("Workspace already exists");
                    } catch (EntityAlreadyExists e) {
                        throw new RuntimeException(e);
                    }
                });
        workspaceRepository.createWorkspace(workspaceCreateDTO);
        return new WorkspaceDisplayDTO(workspaceCreateDTO.workspaceName(), workspaceCreateDTO.capacity());

    }

    public void deleteWorkspace(String id) {
        workspaceRepository.deleteWorkspaceByID(id);
    }

    public WorkspaceDisplayDTO findWorkspaceByID(String id) {
        return workspaceRepository.findWorkspaceByID(id)
                .map(workspace -> new WorkspaceDisplayDTO(workspace.getWorkspaceName(), workspace.getCapacity()))
                .orElseThrow(() -> new NotFoundException("Workspace not found"));
    }

    public WorkspaceDisplayDTO findWorkspaceByName(String name) {
        workspaceRepository.findByWorkspaceName(name);
        return new WorkspaceDisplayDTO(workspaceRepository.findByWorkspaceName(name).getWorkspaceName(),
                workspaceRepository.findByWorkspaceName(name).getCapacity());
    }


    public void deleteWorkspaceByName(String name) {
        workspaceRepository.deleteByWorkspaceName(name);
    }

    public WorkspaceDisplayDTO updateWorkspace(WorkspaceCreateDTO workspaceCreateDTO) {
        workspaceRepository.updateWorkspace(workspaceCreateDTO);
        return new WorkspaceDisplayDTO(workspaceCreateDTO.workspaceName(), workspaceCreateDTO.capacity());
    }



}
