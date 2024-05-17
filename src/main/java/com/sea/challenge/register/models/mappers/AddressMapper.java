package com.sea.challenge.register.models.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sea.challenge.register.models.dtos.AddressDTO;
import com.sea.challenge.register.models.entities.Address;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AddressMapper {
    @Mapping(target = "addressId", ignore = true)
    Address fromDTOToModel(AddressDTO dto);

    AddressDTO fromModelToDTO(Address address);
}