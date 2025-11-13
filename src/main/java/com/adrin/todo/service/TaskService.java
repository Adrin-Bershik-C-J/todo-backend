package com.adrin.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.adrin.todo.dto.request.TaskRequestDto;
import com.adrin.todo.dto.response.TaskResponseDto;
import com.adrin.todo.entity.Task;
import com.adrin.todo.entity.User;
import com.adrin.todo.repository.TaskRepository;
import com.adrin.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponseDto addTask(TaskRequestDto request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .user(user)
                .build();

        taskRepository.save(task);

        return new TaskResponseDto("Task created successfully");
    }    
}


