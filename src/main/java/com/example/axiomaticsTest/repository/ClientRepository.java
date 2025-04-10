package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByPhoneContainingIgnoreCase(String phone);

    List<Client> findByPassport(String passport);

    List<Client> findByFullNameContainingIgnoreCase(String fullName);
}
