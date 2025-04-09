package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}
