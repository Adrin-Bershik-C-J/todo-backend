package com.adrin.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrin.todo.entity.Task;
import com.adrin.todo.entity.User;

public interface TaskRepository extends JpaRepository<Task,Long>{

    List<Task>findByUser(User user);
}
