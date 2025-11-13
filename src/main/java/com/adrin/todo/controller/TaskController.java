package com.adrin.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrin.todo.dto.request.AddTaskRequestDto;
import com.adrin.todo.dto.response.AddTaskResponseDto;
import com.adrin.todo.dto.response.GetTaskResponseDto;
import com.adrin.todo.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    
    @PostMapping("/add")
    public ResponseEntity<AddTaskResponseDto>addTask(@Valid @RequestBody AddTaskRequestDto request){
        return ResponseEntity.ok(taskService.addTask(request));
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<GetTaskResponseDto>>getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
