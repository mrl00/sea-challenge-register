package com.sea.challenge.register.services.impl;

import java.util.List;
import java.util.Optional;

import com.sea.challenge.register.exceptions.CpfAlreadyExistsException;
import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.repositories.ClientRepository;
import com.sea.challenge.register.services.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper mapper;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<ClientDTO> findClientById(Long id) {
        return clientRepository
                .findById(id)
                .map(client -> mapper.fromModelToDTO(client));
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(ClientDTO clientDTO) {
        Client client = mapper.fromRequestDTOToModel(clientDTO);
        if(clientRepository.existsByCpf(client.getCpf()))
            throw new CpfAlreadyExistsException("cpf already exists", client.getCpf());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
