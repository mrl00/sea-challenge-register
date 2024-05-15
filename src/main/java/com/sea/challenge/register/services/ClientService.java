package com.sea.challenge.register.services;

import java.util.List;
import java.util.Optional;

import com.sea.challenge.register.models.dtos.ClientRequestDTO;
import com.sea.challenge.register.models.entities.Client;

public interface ClientService {
    public Optional<ClientRequestDTO> findClientById(Long id);

    public List<Client> findAllClients();

    public Client saveClient(ClientRequestDTO client);

    public void deleteClientById(Long id);
}
