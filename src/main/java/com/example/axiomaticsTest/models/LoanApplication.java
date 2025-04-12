package com.example.axiomaticsTest.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @DecimalMax(value = "1000000000.00", message = "Максимальная сумма — 1.000.000.000")
    @DecimalMin(value = "1000.00", message = "Минимальная сумма — 1000")
    @NotNull(message = "Сумма обязательна")
    @Column(name = "desired_amount")
    private BigDecimal desiredAmount;

    @Size(max = 100, message = "Цель кредита не должна превышать 100 символов")
    @NotBlank(message = "Цель кредита обязательна")
    @Column(name = "credit_purpose")
    private String creditPurpose;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Valid
    @NotNull(message = "Клиент обязателен")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "application")
    private LoanDecision decision;

    @OneToOne(mappedBy = "application")
    private LoanContract contract;
}