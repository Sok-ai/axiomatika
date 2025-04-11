package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanContract;
import com.example.axiomaticsTest.models.LoanDecision;
import com.example.axiomaticsTest.repository.LoanApplicationRepository;
import com.example.axiomaticsTest.repository.LoanContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanContractService {
    private final LoanApplicationRepository applicationRepo;
    private final LoanContractRepository contractRepo;

    public LoanContractService(LoanApplicationRepository applicationRepo, LoanContractRepository contractRepo) {
        this.applicationRepo = applicationRepo;
        this.contractRepo = contractRepo;
    }

    public void signContract(Long applicationId) {
        LoanApplication application = applicationRepo.findById(applicationId).orElse(null);

        LoanDecision decision = application.getDecision();
        if (decision == null || !decision.getApproved()) {
            throw new IllegalStateException("Нельзя подписать неподтверждённую заявку");
        }

        LoanContract contract = new LoanContract();
        contract.setApplication(application);
        contract.setSigned(true);
        contract.setSignedDate(LocalDate.now());

        contractRepo.save(contract);
    }

    public List<LoanContract> getApprovedContracts() {
        return contractRepo.findAll().stream()
                .filter(LoanContract::getSigned)
                .toList();
    }
}
