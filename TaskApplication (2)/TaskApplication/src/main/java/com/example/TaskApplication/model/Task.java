package com.example.TaskApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than or equal to 100 characters")
    private String title;

    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String description;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @JsonIgnore
    private LocalDate dueDate;


    private LocalDate createdAt;
    private LocalDate updatedAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}
