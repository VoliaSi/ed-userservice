package com.volia.example.userservice.mapper;

import com.volia.example.userservice.dto.StudentDto;
import com.volia.example.userservice.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper extends UserMapper<StudentDto, Student> {

    @Override
    Student fromDto(StudentDto dto);

    @Override
    StudentDto toDto(Student student);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(StudentDto dto, @MappingTarget Student student);
}
