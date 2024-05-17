package com.sea.challenge.register.mocks;

import com.sea.challenge.register.models.dtos.AddressDTO;
import com.sea.challenge.register.models.entities.Address;
import com.sea.challenge.register.models.enums.UF;

public class AddressMock {
    public static Address SIMPLE_ADDRESS = getSimpleAddress();
    public static AddressDTO SIMPLE_ADDRESS_DTO = getSimpleAddressDTO();

    private static Address getSimpleAddress() {
        return new Address(1L, "11456000", "logradouro", "bairro", "brasilia", UF.AC, "complemento");
    }

    private static AddressDTO getSimpleAddressDTO() {
        return new AddressDTO("11.456-000", "logradouro", "bairro", "brasilia", UF.AC, "complemento");
    }
}
