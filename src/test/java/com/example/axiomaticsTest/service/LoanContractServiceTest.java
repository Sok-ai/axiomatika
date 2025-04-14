package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanContract;
import com.example.axiomaticsTest.models.LoanDecision;
import com.example.axiomaticsTest.repository.LoanApplicationRepository;
import com.example.axiomaticsTest.repository.LoanContractRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanContractServiceTest {

    @Mock
    private LoanApplicationRepository applicationRepo;

    @Mock
    private LoanContractRepository contractRepo;

    @InjectMocks
    private LoanContractService contractService;

    @Test
    void testSignContract_Approved() {
        LoanApplication app = new LoanApplication();
        LoanDecision decision = new LoanDecision();
        decision.setApproved(true);
        app.setDecision(decision);

        Mockito.when(applicationRepo.findById(1L)).thenReturn(Optional.of(app));

        contractService.signContract(1L);

        Mockito.verify(contractRepo).save(Mockito.any(LoanContract.class));
    }

    @Test
    void testSignContract_NotApproved_Throws() {
        LoanApplication app = new LoanApplication();
        LoanDecision decision = new LoanDecision();
        decision.setApproved(false);
        app.setDecision(decision);

        Mockito.when(applicationRepo.findById(1L)).thenReturn(Optional.of(app));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            contractService.signContract(1L);
        });
    }
}
