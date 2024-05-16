package com.sea.challenge.register.models.mappers;

import com.sea.challenge.register.models.dtos.security.UserDTO;
import com.sea.challenge.register.models.entities.security.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    User fromUserDTOToUserModel(UserDTO authenticationDTO);
}
