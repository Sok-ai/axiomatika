package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPhone(String phone);

    Optional<Client> findByPassport(String passport);

    Optional<Client> findByFullName(String fullName);
}
