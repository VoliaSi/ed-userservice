package com.volia.example.userservice.service;

import com.volia.example.userservice.dto.student.CreateStudentRequest;
import com.volia.example.userservice.dto.student.StudentResponse;
import com.volia.example.userservice.dto.student.UpdateStudentRequest;
import com.volia.example.userservice.exception.UserNotFoundException;
import com.volia.example.userservice.mapper.StudentMapper;
import com.volia.example.userservice.model.Student;
import com.volia.example.userservice.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService extends UserService<
        Student,
        CreateStudentRequest,
        UpdateStudentRequest,
        StudentResponse,
        StudentMapper> {

    private final StudentRepository repository;
    private final StudentMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository repository,
                          StudentMapper mapper,
                          PasswordEncoder passwordEncoder) {
        super(repository, mapper, passwordEncoder);
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected Student mapFromCreate(CreateStudentRequest dto) {
        log.debug("Mapping CreateStudentRequest to Student entity");
        return mapper.fromCreateRequest(dto);
    }

    @Override
    protected String getPasswordFromCreateDto(CreateStudentRequest dto) {
        log.debug("Extracting password from CreateStudentRequest");
        return dto.password();
    }

    @Override
    protected void updateFromDto(UpdateStudentRequest dto, Student user) {
        log.debug("Updating Student entity from UpdateStudentRequest");
        mapper.updateStudentFromRequest(dto, user);
    }

    @Override
    protected StudentResponse mapToResponse(Student user) {
        log.debug("Mapping Student entity to StudentResponse");
        return mapper.toResponse(user);
    }
}
