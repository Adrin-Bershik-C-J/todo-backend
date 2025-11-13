package com.adrin.todo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.adrin.todo.dto.request.AddTaskRequestDto;
import com.adrin.todo.dto.request.UpdateTaskRequestDto;
import com.adrin.todo.dto.response.AddTaskResponseDto;
import com.adrin.todo.dto.response.GetTaskResponseDto;
import com.adrin.todo.entity.Task;
import com.adrin.todo.entity.User;
import com.adrin.todo.exception.TaskNotFoundException;
import com.adrin.todo.exception.UnauthorizedActionException;
import com.adrin.todo.exception.UserNotFoundException;
import com.adrin.todo.repository.TaskRepository;
import com.adrin.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public AddTaskResponseDto addTask(AddTaskRequestDto request){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .user(user)
                .build();

        taskRepository.save(task);

        return new AddTaskResponseDto("Task created successfully");
    }    

    public List<GetTaskResponseDto> getAllTasks(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        List<Task>tasks=taskRepository.findByUser(user);

        return tasks.stream()
        .map(task->GetTaskResponseDto.builder()
        .title(task.getTitle())
        .description(task.getDescription())
        .priority(task.getPriority())
        .status(task.getStatus())
        .createdAt(task.getCreatedAt())
        .build())
        .collect(Collectors.toList());
    } 

    public String updateTask(Long taskId, UpdateTaskRequestDto request){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        Task task = taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You are not authorized to update this task");
        }

        if(request.getTitle()!=null&&!request.getTitle().isBlank()){
            task.setTitle(request.getTitle());
        }

         if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        taskRepository.save(task);

        return "Task updated successfully";
        
    }
}


