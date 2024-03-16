package com.mhp.app.repo;

import com.mhp.app.entity.Workspace;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class WorkspaceRepository implements PanacheRepositoryBase<Workspace, String> {

    public Workspace findByID(String id) {
        return find("id", id).firstResult();
    }

    public void deleteByID(String id) {
        delete("id", id);
    }

    public Workspace findByWorkspaceName(String name) {
        return find("name", name).firstResult();
    }



}
