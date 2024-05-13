package com.sea.challenge.register.mocks;

import com.sea.challenge.register.entities.Address;

public class AddressMock {
    public static Address SIMPLE_ADDRESS = getSimpleAddress();

    private static Address getSimpleAddress() {
        return new Address(1L, "11456000", "logradouro", "bairro", "brasilia", "df", "complemento");
    }
}
