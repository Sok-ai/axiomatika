package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanContractRepository extends JpaRepository<LoanContract, Long> {
    Optional<LoanContract> findByApplication(LoanApplication application);
}
