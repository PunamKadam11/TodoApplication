package com.example.TaskApplication.service.impl;

import com.example.TaskApplication.dto.request.TaskRequest;
import com.example.TaskApplication.dto.response.TaskResponse;
import com.example.TaskApplication.model.Priority;
import com.example.TaskApplication.model.Status;
import com.example.TaskApplication.model.Task;
import com.example.TaskApplication.model.User;
import com.example.TaskApplication.repository.PriorityRepository;
import com.example.TaskApplication.repository.StatusRepository;
import com.example.TaskApplication.repository.TaskRepository;
import com.example.TaskApplication.repository.UserRepository;
import com.example.TaskApplication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskServiceimpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private  StatusRepository statusRepository;
    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = mapToEntity(taskRequest);
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findByActive(true, pageable);
        return tasks.map(this::mapToResponse);
    }


    @Override
    public TaskResponse getTaskById(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToResponse(task);
    }

    @Override
    public TaskResponse updateTask(Integer taskId, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Task updatedTask = mapToEntity(taskRequest);
        updatedTask.setId(existingTask.getId());
        updatedTask.setActive(existingTask.isActive());
        Task savedTask = taskRepository.save(updatedTask);
        return mapToResponse(savedTask);
    }

    @Override
    public void softDeleteTask(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setActive(false);
        taskRepository.save(task);
    }

    @Override
    public Page<TaskResponse> getTasks(String statusName, String priorityName, LocalDate dueDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Status status = statusName != null ? statusRepository.findByName(statusName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + statusName)) : null;

        Priority priority = priorityName != null ? priorityRepository.findByName(priorityName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid priority: " + priorityName)) : null;

        Page<Task> taskPage = taskRepository.findByActiveFilters(status, priority, dueDate, pageable);

        return taskPage.map(this::mapToResponse);
    }

    @Override
    public Page<TaskResponse> searchTasks(String title, String description, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        System.out.println("Searching tasks with title: " + title + " and description: " + description);

        Page<Task> taskPage = taskRepository.searchByTitleOrDescription(title, description, pageable);

        System.out.println("Number of tasks found: " + taskPage.getTotalElements());

        return taskPage.map(this::mapToResponse); // Ensure mapToResponse method is correctly implemented
    }










    private Task mapToEntity(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());


        Status status = statusRepository.findById(taskRequest.getStatusId()).orElseThrow();
        task.setStatus(status);

        Priority priority = priorityRepository.findById(taskRequest.getPriorityId())
                .orElseThrow();
        task.setPriority(priority);

        User user = userRepository.findById(taskRequest.getUserId())
                .orElseThrow();
        task.setUser(user);

        return task;
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setStatusName(task.getStatus().getName());
        taskResponse.setPriorityName(task.getPriority().getName());

        taskResponse.setUserId(task.getUser().getId().longValue());
        return taskResponse;
    }
}
