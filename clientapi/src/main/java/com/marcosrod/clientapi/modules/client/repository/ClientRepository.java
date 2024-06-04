package com.marcosrod.clientapi.modules.client.repository;

import com.marcosrod.clientapi.modules.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByName(String name);
}
