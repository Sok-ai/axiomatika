package com.example.axiomaticsTest.controller;

import com.example.axiomaticsTest.models.Client;
import com.example.axiomaticsTest.models.LoanApplication;
import com.example.axiomaticsTest.models.LoanContract;
import com.example.axiomaticsTest.service.LoanApplicationService;
import com.example.axiomaticsTest.service.LoanContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    private final LoanContractService loanContractService;

    public LoanApplicationController(LoanApplicationService loanApplicationService, LoanContractService loanContractService) {
        this.loanApplicationService = loanApplicationService;
        this.loanContractService = loanContractService;
    }

    @GetMapping("applications/new")
    public String showForm(Model model) {
        LoanApplication application = new LoanApplication();
        application.setClient(new Client());
        model.addAttribute("application", application);
        return "applications/newApplications";
    }

    @PostMapping("applications/new")
    public String submitForm(@ModelAttribute LoanApplication application) {
        loanApplicationService.createApplication(application);
        return "redirect:/applications";
    }

    @GetMapping(path = "applications")
    public String showApplications(Model model) {
        model.addAttribute("loans", loanApplicationService.getAllApplications());
        return "applications/showApplications";
    }

    @GetMapping(path = "applications/approved")
    public String showApprovedApplications(Model model) {
        model.addAttribute("loans", loanApplicationService.getApprovedApplications());
        return "applications/approved";
    }

    @PostMapping("applications/{id}/sign")
    public String signContract(@PathVariable Long id) {
        loanContractService.signContract(id);
        return "redirect:/applications/approved";
    }

    @GetMapping(path = "applications/contracts")
    public String showContracts(Model model) {
        List<LoanContract> signedContracts = loanContractService.getApprovedContracts();
        model.addAttribute("contracts", signedContracts);
        return "contracts/signed-list";
    }
}
