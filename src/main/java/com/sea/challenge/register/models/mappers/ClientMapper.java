package com.sea.challenge.register.models.mappers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.sea.challenge.register.models.dtos.ClientDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.entities.Client;
import com.sea.challenge.register.models.entities.Phone;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ClientMapper {

    @Mapping(target = "clientId", ignore = true)
    @Mapping(target = "address.addressId", ignore = true)
    @Mapping(source = "phones", target = "phones", qualifiedByName = "phonesDTOsToPhoneModels")
    @Mapping(source = "cpf", target = "cpf", qualifiedByName = "unmaskCpf")
    Client fromRequestDTOToModel(ClientDTO dto);

    @Mapping(source = "phones", target = "phones", qualifiedByName = "phoneModelsToPhonesDTOs")
    ClientDTO fromModelToDTO(Client client);

    @Named("phonesDTOsToPhoneModels")
    default List<Phone> fromPhoneToPhoneDTOList(List<PhoneDTO> dtos) {
        return dtos.stream()
                .map(new PhoneMapperImpl()::fromDTOToModel)
                .collect(Collectors.toList());
    }

    @Named("phoneModelsToPhonesDTOs")
    default List<PhoneDTO> fromPhoneModelToPhoneDTO(List<Phone> phones) {
        return phones.stream()
                .map(new PhoneMapperImpl()::fromModelToDTO)
                .collect(Collectors.toList());
    }

    @Named("unmaskCpf")
    default String unmaskCpf(String cpf) {
        return cpf.replaceAll("[\\.\\-]", "");
    }
}
