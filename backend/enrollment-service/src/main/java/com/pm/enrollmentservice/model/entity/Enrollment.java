package com.pm.enrollmentservice.model.entity;

import com.pm.enrollmentservice.model.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "course_name")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID enrollmentId;

    @Column(name = "student_id", nullable = false, unique = true)
    private UUID studentId;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "enrollment_date", nullable = false)
    @CreatedDate
    private LocalDateTime enrollmentDate;
}
