package com.pm.paymentservice.model;

import com.pm.paymentservice.model.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "payment_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "amount", nullable = false, updatable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

}
