package com.sea.challenge.register.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.sea.challenge.register.exceptions.CpfAlreadyExistsException;
import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.mappers.ClientMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sea.challenge.register.mocks.ClientMock;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.repositories.ClientRepository;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void testFindClientByIdWhenIdIsNotNullAndFindClient() {
        Optional<Client> clientToFind = Optional.of(ClientMock.SIMPLE_CLIENT);
        Optional<ClientDTO> clientDTO = clientToFind
                .map(client -> clientMapper.fromModelToDTO(client));

        when(clientRepository.findById(anyLong())).thenReturn(clientToFind);

        Optional<ClientDTO> foundClient = clientService.findClientById(1L);

        assertTrue(foundClient.isPresent());
        assertEquals(foundClient, clientDTO);
    }

    @Test
    public void testFindClientByIdWhenIdIsNotNullAndNotFindClient() {
        Optional<Client> clientToFind = Optional.empty();
        when(clientRepository.findById(any())).thenReturn(clientToFind);

        Optional<ClientDTO> notFoundClient = clientService.findClientById(1L);

        assertTrue(notFoundClient.isEmpty());
    }

    @Test
    public void testFindClientByIdWhenIdIsNull() {
        when(clientRepository.findById(any())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> clientService.findClientById(null));
    }

    @Test
    public void testFindAllClients() {
        List<Client> clients = ClientMock.SIMPLE_CLIENT_LIST;
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> allClients = clientService.findAllClients();
        assertEquals(clients.size(), allClients.size());
    }

    @Test
    public void testSaveClientWhenClientIsNotNull() {
        Client clientToSave = ClientMock.SIMPLE_CLIENT;
        ClientDTO clientDTOToSave = ClientMock.SIMPLE_CLIENT_REQUEST_DTO;

        when(clientRepository.save(any(Client.class))).thenReturn(clientToSave);

        Client savedClient = clientService.saveClient(clientDTOToSave);

        assertNotNull(savedClient);
        assertEquals(clientToSave, savedClient);
    }

    @Test
    @Disabled
    public void testSaveClientWhenClientIsNull() {
        when(clientRepository.save(any()))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> clientService.saveClient(null));
    }

    @Test
    public void testSaveClient_WhenCpfAlreadyExists() {
        ClientDTO clientDTOToSave = ClientMock.SIMPLE_CLIENT_REQUEST_DTO;

        when(clientRepository.existsByCpf(anyString()))
                .thenReturn(true);
        assertThrows(CpfAlreadyExistsException.class, () -> clientService.saveClient(clientDTOToSave));
    }

    @Test
    public void testDeleteClientById() {
        Long id = 1L;
        doNothing().when(clientRepository).deleteById(id);

        clientService.deleteClientById(id);

        verify(clientRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteClientByIdWhenIdIsNull() {
        doThrow(IllegalArgumentException.class).when(clientRepository).deleteById(any());
        assertThrows(IllegalArgumentException.class, () -> clientService.deleteClientById(null));
    }
}
