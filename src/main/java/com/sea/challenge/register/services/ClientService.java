package com.sea.challenge.register.services;

import java.util.List;
import java.util.Optional;

import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.entities.Client;

public interface ClientService {
    public Optional<ClientDTO> findClientById(Long id);

    public List<Client> findAllClients();

    public Client saveClient(ClientDTO client);

    public void deleteClientById(Long id);
}
