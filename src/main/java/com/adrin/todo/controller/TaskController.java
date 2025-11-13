package com.adrin.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrin.todo.dto.request.AddTaskRequestDto;
import com.adrin.todo.dto.request.UpdateTaskRequestDto;
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

    @PatchMapping("/update/{taskId}")
    public ResponseEntity<Map<String,String>>updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskRequestDto request){
        String message = taskService.updateTask(taskId, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponseDto>getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>>deleteTask(@PathVariable Long id){
        String message = taskService.deleteTask(id);
        Map<String,String>response=new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}
