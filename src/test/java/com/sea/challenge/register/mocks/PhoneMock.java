package com.sea.challenge.register.mocks;

import java.util.ArrayList;
import java.util.List;

import com.sea.challenge.register.entities.Phone;
import com.sea.challenge.register.entities.PhoneType;

public class PhoneMock {
    public static List<Phone> SIMPLE_PHONES = getSimplePhone();

    private static List<Phone> getSimplePhone() {
        var phoneList = new ArrayList<Phone>();

        Phone p1 = new Phone(1L, PhoneType.CELPHONE, "61", "98888", "1111");
        Phone p2 = new Phone(2L, PhoneType.CELPHONE, "61", "98888", "2222");

        phoneList.add(p1);
        phoneList.add(p2);

        return phoneList;
    }
}
