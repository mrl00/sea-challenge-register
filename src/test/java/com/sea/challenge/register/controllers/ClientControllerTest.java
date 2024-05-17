package com.sea.challenge.register.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sea.challenge.register.mocks.ClientMock;
import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.services.ClientService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientControllerTest {
    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Client savedClient;
    private ClientDTO clientDTO;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        savedClient = ClientMock.SIMPLE_CLIENT;
        clientDTO = ClientMock.SIMPLE_CLIENT_REQUEST_DTO;
    }

    @Test
    public void findClientByIdTest_WhenClientExists() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(clientService.findClientById(anyLong()))
                .thenReturn(Optional.of(clientDTO));

        mockMvc.perform(get("/register/v1/client/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void findClientByIdTest_WhenClientNotExists() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(clientService.findClientById(anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/register/v1/client/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Order(value = Integer.MAX_VALUE / 2)
    @Test
    public void saveClientTest_Success() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

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
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

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
