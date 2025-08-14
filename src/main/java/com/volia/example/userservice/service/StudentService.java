package com.volia.example.userservice.service;

import com.volia.example.userservice.dto.StudentDto;
import com.volia.example.userservice.mapper.StudentMapper;
import com.volia.example.userservice.model.Student;
import com.volia.example.userservice.model.User;
import com.volia.example.userservice.repository.StudentRepository;
import com.volia.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService extends UserService<Student, StudentDto, StudentMapper> {

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Student mapFromDto(StudentDto dto) {
        return mapper.fromDto(dto);
    }

    @Override
    protected void updateFromDto(StudentDto dto, Student user) {
        mapper.updateFromDto(dto, user);
    }

    @Override
    protected StudentDto mapToDto(Student user) {
        return mapper.toDto(user);
    }

    @Override
    protected String dtoEmail(StudentDto dto) {
        return dto.email();
    }
}
