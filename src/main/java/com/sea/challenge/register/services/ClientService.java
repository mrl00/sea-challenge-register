package com.sea.challenge.register.services;

import java.util.List;
import java.util.Optional;

import com.sea.challenge.register.entities.Client;

public interface ClientService {
    public Optional<Client> findClientById(Long id);

    public List<Client> findAllClients();

    public Client saveClient(Client client);

    public void deleteClientById(Long id);
}
