package com.adrin.todo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8,max = 15,message = "Passwords must be of length 8 to 15")
    private String password;

    @NotBlank(message = "Name can't be blank")
    @Size(max = 20,message = "Name can't exceed 20 characters")
    private String name;
}
