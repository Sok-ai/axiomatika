package com.example.axiomaticsTest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_decision")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean approved;

    @Min(1)
    @Max(12)
    @Column(name = "term_in_months")
    private Integer termInMonths;

    @PositiveOrZero
    @Column(name = "approved_amount")
    private BigDecimal approvedAmount;

    @OneToOne
    @JoinColumn(name = "application_id", unique = true)
    private LoanApplication application;
}