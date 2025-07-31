package com.volia.example.userservice.service;

import com.volia.example.userservice.dto.teacher.CreateTeacherRequest;
import com.volia.example.userservice.dto.teacher.TeacherResponse;
import com.volia.example.userservice.dto.teacher.UpdateTeacherRequest;
import com.volia.example.userservice.mapper.TeacherMapper;
import com.volia.example.userservice.model.Teacher;
import com.volia.example.userservice.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeacherService extends UserService<
        Teacher,
        CreateTeacherRequest,
        UpdateTeacherRequest,
        TeacherResponse,
        TeacherMapper> {

    public TeacherService(TeacherRepository repository,
                          TeacherMapper mapper,
                          PasswordEncoder passwordEncoder) {
        super(repository, mapper, passwordEncoder);
    }

    @Override
    protected Teacher mapFromCreate(CreateTeacherRequest dto) {
        log.debug("Mapping CreateTeacherRequest to Teacher entity");
        return mapper.fromCreateRequest(dto);
    }

    @Override
    protected String getPasswordFromCreateDto(CreateTeacherRequest dto) {
        log.debug("Extracting password from CreateTeacherRequest");
        return dto.password();
    }

    @Override
    protected void updateFromDto(UpdateTeacherRequest dto, Teacher user) {
        log.debug("Updating Teacher entity from UpdateTeacherRequest");
        mapper.updateTeacherFromRequest(dto, user);
    }

    @Override
    protected TeacherResponse mapToResponse(Teacher user) {
        log.debug("Mapping Teacher entity to TeacherResponse");
        return mapper.toResponse(user);
    }
}
