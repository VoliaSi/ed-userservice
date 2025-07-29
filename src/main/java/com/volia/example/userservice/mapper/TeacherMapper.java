package com.volia.example.userservice.mapper;

import com.volia.example.userservice.dto.TeacherDTO;
import com.volia.example.userservice.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Builder;

import java.util.List;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = false)
)
public interface TeacherMapper {

    TeacherDTO toDTO(Teacher teacher);

    Teacher toEntity(TeacherDTO dto);

    List<TeacherDTO> toDTOList(List<Teacher> teachers);

    List<Teacher> toEntityList(List<TeacherDTO> dtos);

    void updateTeacherFromDTO(TeacherDTO dto, @MappingTarget Teacher teacher);
}
