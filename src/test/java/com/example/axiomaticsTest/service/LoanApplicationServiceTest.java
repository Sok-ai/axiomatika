package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.Client;
import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanDecision;
import com.example.axiomaticsTest.repository.ClientRepository;
import com.example.axiomaticsTest.repository.LoanApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTest {

    @Mock
    private LoanApplicationRepository loanApplicationRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private LoanDecisionService loanDecisionService;

    @InjectMocks
    private LoanApplicationService loanApplicationService;

    @Test
    void testGetApprovedApplications() {
        LoanApplication approvedApp = new LoanApplication();
        LoanDecision decision = new LoanDecision();
        decision.setApproved(true);
        approvedApp.setDecision(decision);

        LoanApplication rejectedApp = new LoanApplication();
        LoanDecision rejectedDecision = new LoanDecision();
        rejectedDecision.setApproved(false);
        rejectedApp.setDecision(rejectedDecision);

        Mockito.when(loanApplicationRepository.findAll())
                .thenReturn(List.of(approvedApp, rejectedApp));

        List<LoanApplication> result = loanApplicationService.getApprovedApplications();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testCreateApplication() {
        Client client = new Client();
        client.setId(1L);
        client.setFullName("Иванов Иван");

        LoanApplication app = new LoanApplication();
        app.setClient(client);
        app.setDesiredAmount(BigDecimal.valueOf(100000));
        app.setCreditPurpose("На ремонт");

        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        LoanApplication savedApp = new LoanApplication();
        savedApp.setId(1L);
        savedApp.setClient(client);
        savedApp.setDesiredAmount(app.getDesiredAmount());
        savedApp.setCreditPurpose(app.getCreditPurpose());

        Mockito.when(loanApplicationRepository.save(Mockito.any(LoanApplication.class))).thenReturn(savedApp);

        loanApplicationService.createApplication(app);

        Mockito.verify(loanDecisionService).createLoanDecision(1L);
    }


}