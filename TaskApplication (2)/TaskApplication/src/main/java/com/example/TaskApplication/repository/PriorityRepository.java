package com.example.TaskApplication.repository;

import com.example.TaskApplication.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority,Integer> {

    Optional<Priority> findByName(String name);
}
