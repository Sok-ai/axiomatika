package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanDecision;
import com.example.axiomaticsTest.repository.LoanApplicationRepository;
import com.example.axiomaticsTest.repository.LoanDecisionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanDecisionServiceTest {

    @Mock
    private LoanDecisionRepository decisionRepo;

    @Mock
    private LoanApplicationRepository applicationRepo;

    @InjectMocks
    private LoanDecisionService decisionService;

    @Test
    void testCreateLoanDecision() {
        LoanApplication app = new LoanApplication();
        app.setId(1L);
        app.setDesiredAmount(new BigDecimal("100000"));

        Mockito.when(applicationRepo.findById(1L)).thenReturn(Optional.of(app));

        decisionService.createLoanDecision(1L);

        Mockito.verify(decisionRepo).save(Mockito.any(LoanDecision.class));
        Mockito.verify(applicationRepo).save(app);
    }
}
