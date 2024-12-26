package com.example.blink.repository;

import com.example.blink.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);

    // Updated method signature to accept only one argument for the search query
    List<User> findByUsernameContainingOrNameContaining(String searchQuery, String searchQuery2);

    User findById(long id);
}
