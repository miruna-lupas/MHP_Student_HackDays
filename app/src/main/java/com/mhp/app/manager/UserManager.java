package com.mhp.app.manager;

import com.mhp.app.dto.out.UserDisplayDTO;
import com.mhp.app.entity.User;
import com.mhp.app.repo.UserRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserManager {

    private UserRepo userRepo;

    @Inject
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepo.findById(id);
    }

    public UserDisplayDTO createUser(User user) {
        userRepo.persist(user);
        return new UserDisplayDTO( user.getEmail(), user.getRole());
    }
}
