package com.example.TaskApplication.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private String statusName;

    private String priorityName;

    private LocalDate dueDate;

    private Long userId;
}
