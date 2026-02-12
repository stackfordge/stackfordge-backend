package com.stackfordge.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "contacts",
        indexes = {
                @Index(name = "idx_contacts_created_at", columnList = "createdAt"),
                @Index(name = "idx_contacts_contacted", columnList = "contacted")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String projectType;
    private String budget;
    private String timeline;

    @Column(nullable = false)
    private String message;   // PostgreSQL will map String → TEXT automatically

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean contacted = false;

    @Column(length = 45)
    private String ipAddress;
}