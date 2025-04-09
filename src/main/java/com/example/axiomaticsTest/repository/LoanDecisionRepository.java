package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.model.LoanDecision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDecisionRepository extends JpaRepository<LoanDecision, Long> {
}
