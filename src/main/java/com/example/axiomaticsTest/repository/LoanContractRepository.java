package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.models.LoanContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanContractRepository extends JpaRepository<LoanContract, Long> {
}
