package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.Client;
import com.example.axiomaticsTest.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> searchClients(String phone, String fullName, String passport) {
        if (phone != null && !phone.isEmpty()) {
            return clientRepository.findByPhoneContainingIgnoreCase(phone);
        } else if (fullName != null && !fullName.isEmpty()) {
            return clientRepository.findByFullNameContainingIgnoreCase(fullName);
        } else if (passport != null && !passport.isEmpty()) {
            return clientRepository.findByPassport(passport);
        } else {
            return List.of();
        }
    }
}
