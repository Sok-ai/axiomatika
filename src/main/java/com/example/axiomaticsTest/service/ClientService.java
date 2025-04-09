package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.model.Client;
import com.example.axiomaticsTest.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new IllegalStateException("Нет такого пользователя");
        }
        return client.get();
    }

    public List<Client> searchClients(String phone, String fullName, String passport) {
        if (phone != null && !phone.isEmpty()) {
            return clientRepository.findByPhone(phone);
        } else if (fullName != null && !fullName.isEmpty()) {
            return clientRepository.findByFullNameContainingIgnoreCase(fullName);
        } else if (passport != null && !passport.isEmpty()) {
            return clientRepository.findByPassport(passport);
        } else {
            return List.of();
        }

    }

    public Client saveClient(Client client) {
        if (client == null) {
            throw new IllegalStateException("Пустой пользователь");
        }
        return clientRepository.save(client);
    }
}
