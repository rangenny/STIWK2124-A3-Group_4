package com.stiw2124.assignment1_group4.repository;

import com.stiw2124.assignment1_group4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used by the login endpoint to look up a user by their username
    Optional<User> findByUsername(String username);
}
