package com.sea.challenge.register.services;

import java.util.List;
import java.util.Optional;

import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.entities.Client;

public interface ClientService {
    Optional<ClientDTO> findClientById(Long id);

    List<Client> findAllClients();

    Client saveClient(ClientDTO client);

    void deleteClientById(Long id);
}
