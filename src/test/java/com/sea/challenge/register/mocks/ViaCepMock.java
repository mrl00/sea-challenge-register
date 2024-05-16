package com.sea.challenge.register.mocks;

import com.sea.challenge.register.models.dtos.viacep.ViaCepDTO;

public class ViaCepMock {
    public static ViaCepDTO SIMPLE_VIACEP_DTO = getSimpleViaCepDTO();
    public static ViaCepDTO SIMPLE_VIACEP_DTO_NOTFOUND = getSimpleViaCepDTONotFound();

    private static ViaCepDTO getSimpleViaCepDTO() {
        return ViaCepDTO.builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .complemento("lado ímpar")
                .bairro("Sé")
                .localidade("São Paulo")
                .uf("SP")
                .ibge("3550308")
                .gia("1004")
                .ddd("11")
                .siafi("7107")
                .build();
    }

    private static ViaCepDTO getSimpleViaCepDTONotFound() {
        return ViaCepDTO.builder()
                .erro(true)
                .build();
    }
}
