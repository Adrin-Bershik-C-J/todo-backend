package com.adrin.todo.dto.response;

import java.time.LocalDateTime;

import com.adrin.todo.enums.Priority;
import com.adrin.todo.enums.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTaskResponseDto {

    private Long id;
    
    private String title;

    private String description;

    private Priority priority;

    private Status status;

    private LocalDateTime createdAt;
}
