package com.sea.challenge.register.mocks;

import java.util.Arrays;
import java.util.List;

import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.dtos.EmailDTO;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.models.entities.Email;

public class ClientMock {
    public static Client SIMPLE_CLIENT = getSimpleClient();
    public static ClientDTO SIMPLE_CLIENT_REQUEST_DTO = getSimpleClientRequestDTO();
    public static List<Client> SIMPLE_CLIENT_LIST = getSimpleClientList();

    private static Client getSimpleClient() {
        return new Client(
                1L,
                "clientTest",
                "32259163076",
                AddressMock.SIMPLE_ADDRESS,
                PhoneMock.SIMPLE_PHONES,
                List.of(new Email(1L, "client@mail.com")));
    }

    private static ClientDTO getSimpleClientRequestDTO() {
        return new ClientDTO(
                "clientTest",
                "322.591.630-76",
                List.of(new EmailDTO("client@mail.com")),
                AddressMock.SIMPLE_ADDRESS_DTO,
                PhoneMock.SIMPLE_PHONE_DTOS);
    }

    private static List<Client> getSimpleClientList() {
        Client c1 = new Client(
                1L,
                "client1",
                "12345678900",
                AddressMock.SIMPLE_ADDRESS,
                PhoneMock.SIMPLE_PHONES,
                List.of(new Email(1L,"email1@mail.com")));
        Client c2 = new Client(
                2L,
                "client2",
                "12345678900",
                AddressMock.SIMPLE_ADDRESS,
                PhoneMock.SIMPLE_PHONES,
                List.of(new Email(2L,"email2@mail.com")));
        return Arrays.asList(c1, c2);
    }
}
