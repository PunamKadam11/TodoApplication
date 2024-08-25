package com.example.TaskApplication.service;

import com.example.TaskApplication.dto.request.TaskRequest;
import com.example.TaskApplication.dto.response.TaskResponse;
import com.example.TaskApplication.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);


    Page<TaskResponse> getAllTasks(Pageable pageable);
    TaskResponse getTaskById(Integer taskId);
    TaskResponse updateTask(Integer taskId, TaskRequest taskRequest);
    void softDeleteTask(Integer taskId);


    Page<TaskResponse> getTasks(String statusName, String priorityName, LocalDate dueDate, int page, int size);

    Page<TaskResponse> searchTasks(String title, String description, int page, int size);
}
