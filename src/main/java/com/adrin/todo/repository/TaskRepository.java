package com.adrin.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrin.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
    
}
