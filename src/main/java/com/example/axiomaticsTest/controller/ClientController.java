package com.example.axiomaticsTest.controller;

import com.example.axiomaticsTest.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(path = "clients")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

    @PostMapping("/clients/search")
    public String searchClients(@RequestParam(required = false) String phone, @RequestParam(required = false) String fullName, @RequestParam(required = false) String passport, Model model) {
        if (phone == null && fullName == null && passport == null) {
            model.addAttribute("clients", clientService.getAllClients());
        } else {
            model.addAttribute("clients", clientService.searchClients(phone, fullName, passport));
        }
        return "clients/list";
    }
}
