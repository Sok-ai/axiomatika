package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanDecision;
import com.example.axiomaticsTest.repository.LoanApplicationRepository;
import com.example.axiomaticsTest.repository.LoanDecisionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class LoanDecisionService {
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanDecisionRepository loanDecisionRepository;

    public LoanDecisionService(LoanDecisionRepository loanDecisionRepository, LoanApplicationRepository loanApplicationRepository) {
        this.loanDecisionRepository = loanDecisionRepository;
        this.loanApplicationRepository = loanApplicationRepository;
    }

    public void createLoanDecision(Long applicationId) {
        Random random = new Random();
        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));
        LoanDecision decision = new LoanDecision();
        boolean isApproved = random.nextBoolean();
        decision.setApproved(isApproved);
        if (isApproved) {
            decision.setApprovedAmount(application.getDesiredAmount()
                    .multiply(BigDecimal.valueOf(0.8 + random.nextDouble() * 0.2))
                    .setScale(0, RoundingMode.HALF_UP));
            decision.setTermInMonths(random.nextInt(12) + 1);
        }
        decision.setApplication(application);
        loanDecisionRepository.save(decision);
        application.setDecision(decision);
        loanApplicationRepository.save(application);
    }
}
