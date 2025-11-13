package com.adrin.todo.dto.request;

import com.adrin.todo.enums.Priority;
import com.adrin.todo.enums.Status;

import lombok.Data;

@Data
public class UpdateTaskRequestDto {

    private String title;
    private String description;
    private Priority priority;
    private Status status;
}
