package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.models.LoanDecision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDecisionRepository extends JpaRepository<LoanDecision, Long> {
}
