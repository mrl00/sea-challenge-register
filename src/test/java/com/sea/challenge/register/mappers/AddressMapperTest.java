package com.sea.challenge.register.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sea.challenge.register.mocks.AddressMock;
import com.sea.challenge.register.models.dtos.AddressDTO;
import com.sea.challenge.register.models.entities.Address;
import com.sea.challenge.register.models.mappers.AddressMapper;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test"})
@SpringBootTest
public class AddressMapperTest {

    @Autowired
    private AddressMapper mapper;

    @Test
    public void fromModelToDTOTest() {
        Address address = AddressMock.SIMPLE_ADDRESS;
        AddressDTO dto = mapper.fromModelToDTO(address);

        assertEquals(dto.getCep(), address.getCep());
        assertEquals(dto.getPublicPlace(), address.getPublicPlace());
        assertEquals(dto.getNeighborhood(), address.getNeighborhood());
        assertEquals(dto.getCity(), address.getCity());
        assertEquals(dto.getUf(), address.getUf());
        assertEquals(dto.getComplement(), address.getComplement());
    }

    @Test
    public void fromDTOToModelTest() {
        AddressDTO dto = AddressMock.SIMPLE_ADDRESS_DTO;
        Address address = mapper.fromDTOToModel(dto);

        assertEquals(dto.getCep(), address.getCep());
        assertEquals(dto.getPublicPlace(), address.getPublicPlace());
        assertEquals(dto.getNeighborhood(), address.getNeighborhood());
        assertEquals(dto.getCity(), address.getCity());
        assertEquals(dto.getUf(), address.getUf());
        assertEquals(dto.getComplement(), address.getComplement());
    }
}
