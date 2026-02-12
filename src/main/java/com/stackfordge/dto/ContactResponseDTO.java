package com.stackfordge.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String projectType;
    private String budget;
    private String timeline;
    private String message;
    private boolean contacted;
    private LocalDateTime createdAt;
}
