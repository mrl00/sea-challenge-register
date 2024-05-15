package com.sea.challenge.register.models.mappers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.sea.challenge.register.models.dtos.PhoneRequestDTO;
import com.sea.challenge.register.models.entities.Phone;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PhoneMapper {
    default Phone fromDTOToModel(PhoneRequestDTO dto) {
        Phone phone = new Phone();
        phone.setPhoneType(dto.getPhoneType());

        Pattern pattern = Pattern.compile("^\\((\\d{2})\\)[\\s+](\\d{4,5})[-](\\d{4})$");
        Matcher matcher = pattern.matcher(dto.getPhone());

        if (matcher.matches()) {
            phone.setDdd(matcher.group(1));
            phone.setPrefix(matcher.group(2));
            phone.setSuffix(matcher.group(3));
        }

        return phone;
    }

    default PhoneRequestDTO fromModelToDTO(Phone phone) {
        return new PhoneRequestDTO(phone.getPhoneType(), phone.toString());
    }
}
