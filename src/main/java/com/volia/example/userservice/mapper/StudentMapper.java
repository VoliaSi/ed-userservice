package com.volia.example.userservice.mapper;

import com.volia.example.userservice.dto.student.CreateStudentRequest;
import com.volia.example.userservice.dto.student.StudentResponse;
import com.volia.example.userservice.dto.student.UpdateStudentRequest;
import com.volia.example.userservice.model.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponse toResponse(Student student);

    List<StudentResponse> toResponseList(List<Student> students);

    Student fromCreateRequest(CreateStudentRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromRequest(UpdateStudentRequest dto, @MappingTarget Student student);
}
