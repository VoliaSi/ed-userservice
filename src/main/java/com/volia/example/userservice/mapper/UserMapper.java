package com.volia.example.userservice.mapper;

import com.volia.example.userservice.model.User;
import org.mapstruct.MappingTarget;

public interface UserMapper<Dto, U extends User> {

    U fromDto(Dto dto);

    Dto toDto(U user);

    void updateFromDto(Dto dto, @MappingTarget U user);
}
