package com.volia.example.userservice.service;

import com.volia.example.userservice.dto.TeacherDto;
import com.volia.example.userservice.mapper.TeacherMapper;
import com.volia.example.userservice.model.Teacher;
import com.volia.example.userservice.model.User;
import com.volia.example.userservice.repository.TeacherRepository;
import com.volia.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeacherService extends UserService<Teacher, TeacherDto, TeacherMapper> {

    public TeacherService(TeacherRepository repository, TeacherMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Teacher mapFromDto(TeacherDto dto) {
        return mapper.fromDto(dto);
    }

    @Override
    protected void updateFromDto(TeacherDto dto, Teacher user) {
        mapper.updateFromDto(dto, user);
    }

    @Override
    protected TeacherDto mapToDto(Teacher user) {
        return mapper.toDto(user);
    }

    @Override
    protected String dtoEmail(TeacherDto dto) {
        return dto.email();
    }
}
