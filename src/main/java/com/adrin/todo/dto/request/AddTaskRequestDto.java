package com.adrin.todo.dto.request;

import com.adrin.todo.enums.Priority;
import com.adrin.todo.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequestDto {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must be within 100 characters")
    private String title;

    @Size(max = 500, message = "Description can be up to 500 characters only")
    private String description;

    @NotNull(message = "Priority must be specified (LOW, MEDIUM, or HIGH)")
    private Priority priority;

    @NotNull(message = "Status must be specified (PENDING, IN_PROGRESS, or COMPLETED)")
    private Status status;
}
