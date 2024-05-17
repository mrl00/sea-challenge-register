package com.sea.challenge.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sea.challenge.register.models.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
