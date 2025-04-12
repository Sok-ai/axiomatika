package com.example.axiomaticsTest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "loan_contract")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "signed_date")
    private LocalDate signedDate;

    @NotNull
    @Column(name = "signed", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean signed = false;

    @OneToOne
    @JoinColumn(name = "application_id", unique = true)
    private LoanApplication application;
}