package com.example.axiomaticsTest.repository;

import com.example.axiomaticsTest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
