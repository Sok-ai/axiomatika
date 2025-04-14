package com.example.axiomaticsTest.service;

import com.example.axiomaticsTest.models.Client;
import com.example.axiomaticsTest.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testGetAllClients() {
        List<Client> mockClients = List.of(new Client(), new Client());
        Mockito.when(clientRepository.findAll()).thenReturn(mockClients);

        List<Client> result = clientService.getAllClients();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testSearchByPhone() {
        Client client = new Client();
        client.setPhone("89101112233");

        Mockito.when(clientRepository.findByPhoneContainingIgnoreCase("8910")).thenReturn(List.of(client));

        List<Client> result = clientService.searchClients("8910", null, null);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testSearchByFullName() {
        Client client = new Client();
        client.setFullName("Иван Иванов");

        Mockito.when(clientRepository.findByFullNameContainingIgnoreCase("Иван")).thenReturn(List.of(client));

        List<Client> result = clientService.searchClients(null, "Иван", null);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testSearchByPassport() {
        Client client = new Client();
        client.setPassport("1234567890");

        Mockito.when(clientRepository.findByPassport("1234567890")).thenReturn(List.of(client));

        List<Client> result = clientService.searchClients(null, null, "1234567890");
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testSearchWithEmptyParams() {
        List<Client> result = clientService.searchClients(null, null, null);
        Assertions.assertTrue(result.isEmpty());
    }
}