package com.mhp.app.repo;

import com.mhp.app.dto.out.UserDisplayDTO;
import com.mhp.app.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepo implements PanacheRepositoryBase<User, Long> {

    @Transactional
    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    @Transactional
    public Optional<User> findUserByID(Long id) {
        return findByIdOptional(id);
    }


    @Transactional
    public User createUser(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        persist(user);
        return user;
    }

    @Transactional
    public void deleteUserById(Long id) {
        deleteById(id);
    }

    @Transactional
    public List<UserDisplayDTO> getAllUsers() {
        return listAll().stream()
                .map(user ->
                        new UserDisplayDTO(user.getEmail(),
                                user.getRole())).toList();
    }
}
