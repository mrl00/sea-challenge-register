package com.sea.challenge.register.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sea.challenge.register.mocks.PhoneMock;
import com.sea.challenge.register.models.dtos.PhoneRequestDTO;
import com.sea.challenge.register.models.entities.Phone;
import com.sea.challenge.register.models.enums.PhoneType;
import com.sea.challenge.register.models.mappers.PhoneMapper;

@SpringBootTest
public class PhoneMapperTest {

    @Autowired
    private PhoneMapper mapper;

    @Test
    public void fromDTOToModelTest() {
        PhoneRequestDTO dto = PhoneMock.SIMPLE_PHONE_DTO;
        Phone phone = mapper.fromDTOToModel(dto);

        assertEquals(phone.getPhoneType(), PhoneType.CELPHONE);
        assertEquals(phone.getDdd(), "61");
        assertEquals(phone.getPrefix(), "98888");
        assertEquals(phone.getSuffix(), "7777");
    }

}
