package com.sea.challenge.register.controllers;

import com.sea.challenge.register.exceptions.viacep.CepNotFoundException;
import com.sea.challenge.register.mocks.ViaCepMock;
import com.sea.challenge.register.models.dtos.viacep.ViaCepDTO;
import com.sea.challenge.register.services.consumer.viacep.ViaCepConsumerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ViaCepController.class)
public class ViaCepControllerTest {
    @MockBean
    private ViaCepConsumerService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenAValidCep_thenOk() throws Exception {
        ViaCepDTO viaCepDTO = ViaCepMock.SIMPLE_VIACEP_DTO;

        when(service.getCepDTO(anyString()))
                .thenReturn(viaCepDTO);

        mockMvc.perform(get("/register/v1/cep/{cep}", "01001000"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAInvalidCep_thenBadRequest() throws Exception {
        ViaCepDTO viaCepDTO = ViaCepMock.SIMPLE_VIACEP_DTO;

        when(service.getCepDTO(anyString()))
                .thenReturn(viaCepDTO);

        mockMvc.perform(get("/register/v1/cep/{cep}", "11"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAValidCep_thenCepNotFound() throws Exception {
        ViaCepDTO viaCepDTO = ViaCepMock.SIMPLE_VIACEP_DTO_NOTFOUND;

        when(service.getCepDTO(anyString()))
                .thenReturn(viaCepDTO);

        mockMvc.perform(get("/register/v1/cep/{cep}", "01001000"))
                .andExpect(status().isNotFound());
    }
}
