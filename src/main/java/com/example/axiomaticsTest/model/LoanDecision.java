package com.example.axiomaticsTest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan_decision")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "application_id")
    private Integer applicationId;
    private Boolean approved;
    private Double approved_amount;
    private Integer term_months;
    private LocalDateTime decision_date;
}
