package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByPhoneContainingIgnoreCase(String phone);

    List<Client> findByPassport(String passport);

    List<Client> findByFullNameContainingIgnoreCase(String fullName);
}
