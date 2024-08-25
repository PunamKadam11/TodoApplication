package com.example.TaskApplication.controller;

import com.example.TaskApplication.dto.request.TaskRequest;
import com.example.TaskApplication.dto.response.TaskResponse;
import com.example.TaskApplication.model.Task;
import com.example.TaskApplication.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/task")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest task) {
        TaskResponse createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/all-task")
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<TaskResponse> tasks = taskService.getAllTasks(PageRequest.of(page, size));
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/taskById/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Integer id, @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.updateTask(id, taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> softDeleteTask(@PathVariable Integer id) {
        taskService.softDeleteTask(id);
        return ResponseEntity.ok("Task with ID " + id + " has been deleted successfully.");
    }
    @GetMapping("/filter-task")
    public ResponseEntity<Page<TaskResponse>> getTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (dueDate == null) {
            System.out.println("Due date is null");
        }

        Page<TaskResponse> tasks = taskService.getTasks(status, priority, dueDate, page, size);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponse>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<TaskResponse> tasks = taskService.searchTasks(title, description, page, size);
        return ResponseEntity.ok(tasks);
    }

}
