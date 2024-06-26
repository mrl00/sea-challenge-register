package com.sea.challenge.register.models.mappers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.entities.Phone;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PhoneMapper {
    default Phone fromDTOToModel(PhoneDTO dto) {
        Phone phone = new Phone();
        phone.setPhoneType(dto.getPhoneType());

        Pattern pattern = Pattern.compile("^\\((\\d{2})\\)[\\s+](\\d{4,5})[-](\\d{4})$");
        Matcher matcher = pattern.matcher(dto.getPhone());

        if (matcher.matches())
            phone.setPhone(matcher.group(1) + matcher.group(2) + matcher.group(3));

        return phone;
    }

    default PhoneDTO fromModelToDTO(Phone phone) {
        return new PhoneDTO(phone.getPhoneType(), phone.toString());
    }
}
