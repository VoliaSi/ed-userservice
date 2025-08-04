package com.volia.example.userservice.controller;

import com.volia.example.userservice.dto.TeacherDto;
import com.volia.example.userservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@Slf4j
public class TeacherController extends UserController<TeacherDto> {

    private final TeacherService teacherService;

    @Override
    protected TeacherService getService() {
        return teacherService;
    }
}
