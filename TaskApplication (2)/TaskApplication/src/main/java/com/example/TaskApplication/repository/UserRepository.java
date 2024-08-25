package com.example.TaskApplication.repository;


import com.example.TaskApplication.model.Role;
import com.example.TaskApplication.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByUsername(String username);
    Optional<User> findByRole(Role role);


}
