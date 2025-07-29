package com.volia.example.userservice.mapper;

import com.volia.example.userservice.dto.teacher.CreateTeacherRequest;
import com.volia.example.userservice.dto.teacher.TeacherResponse;
import com.volia.example.userservice.dto.teacher.UpdateTeacherRequest;
import com.volia.example.userservice.model.Teacher;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper
{

    TeacherResponse toResponse(Teacher teacher);

    List<TeacherResponse> toResponseList(List<Teacher> teachers);

    Teacher fromCreateRequest(CreateTeacherRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromRequest(UpdateTeacherRequest dto, @MappingTarget Teacher teacher);
}
