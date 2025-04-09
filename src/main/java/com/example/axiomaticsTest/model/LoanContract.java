package com.example.axiomaticsTest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan_contract")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "application_id")
    private Integer applicationId;
    private Boolean signed;
    private LocalDateTime signed_at;
}
