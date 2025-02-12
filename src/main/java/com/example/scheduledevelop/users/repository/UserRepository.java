package com.example.scheduledevelop.users.repository;

import com.example.scheduledevelop.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//유저repository

    Optional<User> findUserById(Long Id);

 //   Optional<User> findByEmailAndPassword(String username, String password);

    default User findUserByIdOrElseThrow(Long Id) {
        return findUserById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exit userId " + Id));
    }

    User findByEmailAndPassword(String email, String password);
}
