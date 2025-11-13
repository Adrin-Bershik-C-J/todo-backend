package com.adrin.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrin.todo.dto.request.TaskRequestDto;
import com.adrin.todo.dto.response.TaskResponseDto;
import com.adrin.todo.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    
    @PostMapping("/add")
    public ResponseEntity<TaskResponseDto>addTask(@Valid @RequestBody TaskRequestDto request){
        return ResponseEntity.ok(taskService.addTask(request));
    }
}
