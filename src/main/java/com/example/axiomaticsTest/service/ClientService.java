package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.model.Client;
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

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Клиент не найден"));
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

    public void saveClient(Client client) {
        if (client == null) {
            throw new IllegalStateException("Пустой пользователь");
        }
        clientRepository.save(client);
    }
}
