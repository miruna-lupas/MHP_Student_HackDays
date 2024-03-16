package com.mhp.app.repo;

import com.mhp.app.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepo implements PanacheRepositoryBase<User, Long> {

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Optional<User> findUserByID(Long id) {
        return findByIdOptional(id);
    }

    //create user but check before if their email dos not exist otherwise throw an exception
    public User createUser(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        persist(user);
        return user;
    }
}
