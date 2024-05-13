package com.sea.challenge.register.mocks;

import java.util.Arrays;
import java.util.List;

import com.sea.challenge.register.entities.Client;

public class ClientMock {
    public static Client SIMPLE_CLIENT = getSimpleClient();
    public static List<Client> SIMPLE_CLIENT_LIST = getSimpleClientList();

    private static Client getSimpleClient() {
        return new Client(
                1L,
                "an client",
                "12345678900",
                AddressMock.SIMPLE_ADDRESS,
                PhoneMock.SIMPLE_PHONES,
                "email@mail.com");
    }

    private static List<Client> getSimpleClientList() {
        Client c1 = new Client(
                1L,
                "client1",
                "12345678900",
                AddressMock.SIMPLE_ADDRESS,
                PhoneMock.SIMPLE_PHONES,
                "email1@mail.com");
        Client c2 = new Client(
                2L,
                "client2",
                "12345678900",
                AddressMock.SIMPLE_ADDRESS,
                PhoneMock.SIMPLE_PHONES,
                "email2@mail.com");
        return Arrays.asList(c1, c2);
    }
}
