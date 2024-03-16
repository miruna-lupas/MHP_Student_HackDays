package com.mhp.app.manager;

import com.mhp.app.dto.out.UserDisplayDTO;
import com.mhp.app.entity.User;
import com.mhp.app.repo.UserRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserManager {

    private UserRepo userRepo;

    @Inject
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepo.findByEmail(email));
    }

    public Optional<User> findById(Long id) {
        return userRepo.findByIdOptional(id);
    }

    public UserDisplayDTO createUser(User user) {
        userRepo.persist(user);
        return new UserDisplayDTO(user.getEmail(), user.getRole());
    }


    public List<User> getAllUsers() {
        return userRepo.listAll();
    }


    public void updateUser(User user) {
        userRepo.persist(user);
    }

    public UserDisplayDTO getUserById(Long id) {
        var user = userRepo.findByIdOptional(id).orElseThrow();
        return new UserDisplayDTO(user.getEmail(), user.getRole());
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }


}
