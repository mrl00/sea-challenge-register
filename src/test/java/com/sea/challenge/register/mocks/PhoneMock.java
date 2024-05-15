package com.sea.challenge.register.mocks;

import java.util.Arrays;
import java.util.List;

import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.entities.Phone;
import com.sea.challenge.register.models.enums.PhoneType;

public class PhoneMock {
    public static Phone SIMPLE_PHONE = getSimplePhone();
    public static List<Phone> SIMPLE_PHONES = getSimplePhones();

    public static PhoneDTO SIMPLE_PHONE_DTO = getSimplePhoneDTO();
    public static List<PhoneDTO> SIMPLE_PHONE_DTOS = getSimplePhoneDTOs();

    private static List<Phone> getSimplePhones() {
        Phone p1 = new Phone(1L, PhoneType.CELPHONE, "61", "98888", "1111");
        Phone p2 = new Phone(1L, PhoneType.CELPHONE, "61", "98888", "2222");

        return Arrays.asList(p1,p2);
    }

    private static Phone getSimplePhone() {
        return new Phone(1L, PhoneType.CELPHONE, "61", "98888", "1111");
    }

    private static List<PhoneDTO> getSimplePhoneDTOs() {
        PhoneDTO p1 = new PhoneDTO(PhoneType.CELPHONE, "(61) 98888-1111");
        PhoneDTO p2 = new PhoneDTO(PhoneType.CELPHONE, "(61) 98888-2222");

        return Arrays.asList(p1, p2);
    }

    private static PhoneDTO getSimplePhoneDTO() {
        return new PhoneDTO(PhoneType.CELPHONE, "(61) 98888-7777");
    }
}
