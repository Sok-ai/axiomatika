package com.example.axiomaticsTest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan_application")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer client_id;
    private Double desired_amount;
    private String credit_purpose;
    private LocalDateTime create_at;
}
