package com.mhp.app.repo;

import com.mhp.app.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepo implements PanacheRepositoryBase<User, Long> {

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public User findById(Long id) {
        return find("id", id).firstResult();
    }

}
