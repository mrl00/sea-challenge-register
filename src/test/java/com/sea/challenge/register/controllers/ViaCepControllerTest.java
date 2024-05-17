package com.sea.challenge.register.controllers;

import com.sea.challenge.register.mocks.ViaCepMock;
import com.sea.challenge.register.models.dtos.viacep.ViaCepDTO;
import com.sea.challenge.register.services.consumer.viacep.ViaCepConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
public class ViaCepControllerTest {
    @MockBean
    private ViaCepConsumerService service;

    @MockBean
    Authentication authentication;

    @MockBean
    SecurityContext securityContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void givenAValidCep_thenOk() throws Exception {
        ViaCepDTO viaCepDTO = ViaCepMock.SIMPLE_VIACEP_DTO;

        when(authentication.getName()).thenReturn("user");
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(service.getCepDTO(anyString()))
                .thenReturn(viaCepDTO);

        mockMvc.perform(get("/register/v1/cep/{cep}", "01001000"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAInvalidCep_thenBadRequest() throws Exception {
        ViaCepDTO viaCepDTO = ViaCepMock.SIMPLE_VIACEP_DTO;

        when(authentication.getName()).thenReturn("user");
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(service.getCepDTO(anyString()))
                .thenReturn(viaCepDTO);

        mockMvc.perform(get("/register/v1/cep/{cep}", "11"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAValidCep_thenCepNotFound() throws Exception {
        ViaCepDTO viaCepDTO = ViaCepMock.SIMPLE_VIACEP_DTO_NOTFOUND;

        when(authentication.getName()).thenReturn("user");
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(service.getCepDTO(anyString()))
                .thenReturn(viaCepDTO);

        mockMvc.perform(get("/register/v1/cep/{cep}", "01001000"))
                .andExpect(status().isNotFound());
    }
}
