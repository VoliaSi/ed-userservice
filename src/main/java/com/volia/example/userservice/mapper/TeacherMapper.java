package com.volia.example.userservice.mapper;

import com.volia.example.userservice.dto.TeacherDto;
import com.volia.example.userservice.model.Teacher;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends UserMapper<TeacherDto, Teacher> {

    @Override
    Teacher fromDto(TeacherDto dto);

    @Override
    TeacherDto toDto(Teacher teacher);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(TeacherDto dto, @MappingTarget Teacher teacher);
}
