package com.example.TaskApplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than or equal to 100 characters")
    private String title;

    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String description;

    @NotNull(message = "Status ID is mandatory")
    private Integer statusId;

    @NotNull(message = "Priority ID is mandatory")
    private Integer priorityId;

    private LocalDate dueDate;

    @NotNull(message = "User ID is mandatory")
    private Long userId;
}
