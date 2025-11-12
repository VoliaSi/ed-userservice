package com.volia.example.userservice.mapper;

import com.volia.example.userservice.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface UserMapper<Dto, U extends User> {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    U fromDto(Dto dto);

    Dto toDto(U user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(Dto dto, @MappingTarget U user);
}
