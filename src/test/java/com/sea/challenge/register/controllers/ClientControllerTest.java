package com.sea.challenge.register.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sea.challenge.register.mocks.ClientMock;
import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.services.ClientService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Client savedClient;
    private ClientDTO clientDTO;

    @BeforeEach
    public void setup() {
        savedClient = ClientMock.SIMPLE_CLIENT;
        clientDTO = ClientMock.SIMPLE_CLIENT_REQUEST_DTO;
    }

    @Test
    public void findClientByIdTest_WhenClientExists() throws Exception {
        when(clientService.findClientById(anyLong()))
                .thenReturn(Optional.of(clientDTO));

        mockMvc.perform(get("/register/v1/client/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void findClientByIdTest_WhenClientNotExists() throws Exception {
        when(clientService.findClientById(anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/register/v1/client/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Order(value = Integer.MAX_VALUE / 2)
    @Test
    public void saveClientTest_Success() throws Exception {
        when(clientService.saveClient(any(ClientDTO.class)))
                .thenReturn(savedClient);
        clientDTO.getAddress().setCep("11.456-000");
        var jsonClientDTO = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/register/v1/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonClientDTO))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", CoreMatchers.containsString("/register/v1/client/1")));
    }

    @Test
    public void saveClientTest_WhenPhoneOrAddressAreInvalid() throws Exception {
        //set invalid cep for dto
        clientDTO.getAddress().setCep("11456000");

        when(clientService.saveClient(any(ClientDTO.class)))
                .thenReturn(savedClient);

        mockMvc.perform(post("/register/v1/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isBadRequest());
    }
}
