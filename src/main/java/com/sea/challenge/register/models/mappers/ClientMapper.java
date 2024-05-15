package com.sea.challenge.register.models.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sea.challenge.register.models.dtos.ClientRequestDTO;
import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.models.entities.Phone;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ClientMapper {

    @Mapping(target = "clientId", ignore = true)
    @Mapping(target = "address.addressId", ignore = true)
    @Mapping(source = "phones", target = "phones", qualifiedByName = "phonesDTOsToPhoneModels")
    Client fromRequestDTOToModel(ClientRequestDTO dto);

    @Mapping(source = "phones", target = "phones", qualifiedByName = "PhoneModelsTophonesDTOs")
    ClientRequestDTO fromModelToDTO(Client client);

    @Named("phonesDTOsToPhoneModels")
    default List<Phone> fromPhoneToPhoneDTOList(List<PhoneDTO> dtos) {
        return dtos.stream()
                .map(new PhoneMapperImpl()::fromDTOToModel)
                .collect(Collectors.toList());
    }

    @Named("PhoneModelsTophonesDTOs")
    default List<PhoneDTO> fromPhoneModelToPhoneDTO(List<Phone> phones) {
        return phones.stream()
                .map(new PhoneMapperImpl()::fromModelToDTO)
                .collect(Collectors.toList());
    }
}
