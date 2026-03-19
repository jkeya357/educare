package com.pm.courseservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "course_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "course_owner", nullable = false)
    private UUID courseOwner;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
}