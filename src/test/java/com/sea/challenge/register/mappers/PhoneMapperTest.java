package com.sea.challenge.register.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sea.challenge.register.mocks.PhoneMock;
import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.entities.Phone;
import com.sea.challenge.register.models.enums.PhoneType;
import com.sea.challenge.register.models.mappers.PhoneMapper;

@SpringBootTest
public class PhoneMapperTest {

    @Autowired
    private PhoneMapper mapper;

    @Test
    public void fromDTOToModelTest() {
        PhoneDTO dto = PhoneMock.SIMPLE_PHONE_DTO;
        Phone phone = mapper.fromDTOToModel(dto);

        assertEquals(phone.getPhoneType(), PhoneType.CELLPHONE);
        assertEquals(phone.getDdd(), "61");
        assertEquals(phone.getPrefix(), "98888");
        assertEquals(phone.getSuffix(), "7777");
    }

    @Test
    public void fromModelToDTO() {
        Phone phone = PhoneMock.SIMPLE_PHONE;
        PhoneDTO dto = mapper.fromModelToDTO(phone);
        assertEquals(phone.toString(), dto.getPhone());
        assertEquals(phone.getPhoneType(), dto.getPhoneType());
    }

}
