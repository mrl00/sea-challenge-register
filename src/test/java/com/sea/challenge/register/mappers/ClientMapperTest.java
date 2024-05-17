package com.sea.challenge.register.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import com.sea.challenge.register.models.dtos.ClientDTO;
import com.sea.challenge.register.models.dtos.EmailDTO;
import com.sea.challenge.register.models.entities.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sea.challenge.register.mocks.ClientMock;
import com.sea.challenge.register.models.dtos.AddressDTO;
import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.entities.Address;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.models.entities.Phone;
import com.sea.challenge.register.models.mappers.ClientMapper;

@SpringBootTest
public class ClientMapperTest {
    @Autowired
    private ClientMapper mapper;

    @Test
    public void fromRequestDTOToModelTest() {
        ClientDTO clientDTO = ClientMock.SIMPLE_CLIENT_REQUEST_DTO;
        Client client = mapper.fromRequestDTOToModel(clientDTO);

        AddressDTO addressDTO = clientDTO.getAddress();
        Address address = client.getAddress();

        List<PhoneDTO> phonesDTO = clientDTO.getPhones();
        List<Phone> phones = client.getPhones();

        // Client Fields
        assertEquals(clientDTO.getName(), client.getName());
        assertIterableEquals(
                clientDTO.getEmails().stream().map(EmailDTO::getEmail).toList(),
                client.getEmails().stream().map(Email::getEmail).toList());
        assertEquals(clientDTO.getCpf().replaceAll("[\\.\\-]", ""), client.getCpf());

        // Client Address
        assertEquals(addressDTO.getCep(), address.getCep());
        assertEquals(addressDTO.getPublicPlace(), address.getPublicPlace());
        assertEquals(addressDTO.getNeighborhood(), address.getNeighborhood());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getUf(), address.getUf());
        assertEquals(addressDTO.getComplement(), address.getComplement());

        // Client Phones
        assertEquals(phonesDTO.get(0).getPhone(), phones.get(0).toString());
        assertEquals(phonesDTO.get(1).getPhone(), phones.get(1).toString());
    }

    @Test
    public void fromModelToDTOTest() {
        Client client = ClientMock.SIMPLE_CLIENT;
        ClientDTO clientDTO = mapper.fromModelToDTO(client);

        Address address = client.getAddress();
        AddressDTO addressDTO = clientDTO.getAddress();

        List<Phone> phones = client.getPhones();
        List<PhoneDTO> phonesDTO = clientDTO.getPhones();

        // Client Fields
        assertEquals(clientDTO.getName(), client.getName());
        assertEquals(clientDTO.getCpf(), mapper.maskCpf(client.getCpf()));
        assertIterableEquals(
                clientDTO.getEmails().stream().map(EmailDTO::getEmail).toList(),
                client.getEmails().stream().map(Email::getEmail).toList());

        // Client Address
        assertEquals(addressDTO.getCep(), address.getCep());
        assertEquals(addressDTO.getPublicPlace(), address.getPublicPlace());
        assertEquals(addressDTO.getNeighborhood(), address.getNeighborhood());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getUf(), address.getUf());
        assertEquals(addressDTO.getComplement(), address.getComplement());

        // Client Phones
        assertEquals(phonesDTO.get(0).getPhone(), phones.get(0).toString());
    }
}
