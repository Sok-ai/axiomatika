package com.example.axiomaticsTest.controller;

import com.example.axiomaticsTest.model.Client;
import com.example.axiomaticsTest.model.LoanApplication;
import com.example.axiomaticsTest.service.LoanApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
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
        model.addAttribute("loads", loanApplicationService.getAllApplications());
        return "applications/showApplications";
    }

    @GetMapping(path = "applications/approved")
    public String showApprovedApplications(Model model) {
        model.addAttribute("loads", loanApplicationService.getApprovedApplications());
        return "applications/approved";
    }
}
