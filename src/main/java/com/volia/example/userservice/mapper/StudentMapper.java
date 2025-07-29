package com.volia.example.userservice.mapper;

import com.volia.example.userservice.dto.StudentDTO;
import com.volia.example.userservice.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Builder;

import java.util.List;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = false)
)

public interface StudentMapper {

    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);

    List<StudentDTO> toDTOList(List<Student> students);

    List<Student> toEntityList(List<StudentDTO> dtos);

    void updateStudentFromDTO(StudentDTO dto, @MappingTarget Student student);
}
