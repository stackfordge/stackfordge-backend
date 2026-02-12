package com.stackfordge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class ContactRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Project type is required")
    private String projectType;

    private String budget;

    private String timeline;

    @NotBlank(message = "Message is required")
    private String message;
}
