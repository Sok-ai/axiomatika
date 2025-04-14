package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.Client;
import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.repository.ClientRepository;
import com.example.axiomaticsTest.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {
    private final LoanApplicationRepository loanApplicationRepository;
    private final ClientRepository clientRepository;
    private final LoanDecisionService loanDecisionService;


    public LoanApplicationService(LoanDecisionService loanDecisionService, LoanApplicationRepository loanApplicationRepository, ClientRepository clientRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.clientRepository = clientRepository;
        this.loanDecisionService = loanDecisionService;
    }

    public List<LoanApplication> getAllApplications() {
        return loanApplicationRepository.findAll();
    }

    public List<LoanApplication> getApprovedApplications() {
        return loanApplicationRepository.findAll()
                .stream()
                .filter(loan -> loan.getDecision() != null && loan.getDecision().getApproved())
                .toList();
    }

    public void createApplication(LoanApplication application) {
        Client savedClient = clientRepository.save(application.getClient());

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setClient(savedClient);
        loanApplication.setDesiredAmount(application.getDesiredAmount());
        loanApplication.setCreditPurpose(application.getCreditPurpose());

        LoanApplication savedApp = loanApplicationRepository.save(loanApplication);

        loanDecisionService.createLoanDecision(savedApp.getId());
    }
}
