package com.example.TaskApplication.repository;

import com.example.TaskApplication.model.Priority;
import com.example.TaskApplication.model.Status;
import com.example.TaskApplication.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Page<Task> findByActive(boolean b, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE "
            + "(:status IS NULL OR t.status = :status) AND "
            + "(:priority IS NULL OR t.priority = :priority) AND "
            + "(:dueDate IS NULL OR t.dueDate = :dueDate) AND "
            + "t.active = true")
    Page<Task> findByActiveFilters(@Param("status") Status status,
                                   @Param("priority") Priority priority,
                                   @Param("dueDate") LocalDate dueDate,
                                   Pageable pageable);

    @Query("SELECT t FROM Task t WHERE "
            + "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND "
            + "(:description IS NULL OR LOWER(t.description) LIKE LOWER(CONCAT('%', :description, '%'))) AND "
            + "t.active = true")
    Page<Task> searchByTitleOrDescription(@Param("title") String title,
                                          @Param("description") String description,
                                          Pageable pageable);



}

