package com.sea.challenge.register.mocks;

import java.util.ArrayList;
import java.util.List;

import com.sea.challenge.register.models.dtos.PhoneRequestDTO;
import com.sea.challenge.register.models.entities.Phone;
import com.sea.challenge.register.models.enums.PhoneType;

public class PhoneMock {
    public static List<Phone> SIMPLE_PHONES = getSimplePhone();
    public static PhoneRequestDTO SIMPLE_PHONE_DTO = getSimplePhoneDTO();

    private static List<Phone> getSimplePhone() {
        var phoneList = new ArrayList<Phone>();

        Phone p1 = new Phone(1L, PhoneType.CELPHONE, "61", "98888", "1111");
        Phone p2 = new Phone(2L, PhoneType.CELPHONE, "61", "98888", "2222");

        phoneList.add(p1);
        phoneList.add(p2);

        return phoneList;
    }

    private static PhoneRequestDTO getSimplePhoneDTO() {
        return new PhoneRequestDTO(PhoneType.CELPHONE, "(61) 98888-7777");
    }
}
