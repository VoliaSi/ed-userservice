package com.volia.example.userservice.controller;

import com.volia.example.userservice.dto.teacher.CreateTeacherRequest;
import com.volia.example.userservice.dto.teacher.TeacherResponse;
import com.volia.example.userservice.dto.teacher.UpdateTeacherRequest;
import com.volia.example.userservice.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public TeacherResponse createTeacher(@RequestBody @Valid CreateTeacherRequest dto) {
        log.info("Received request to create teacher with email: {}", dto.email());
        TeacherResponse response = teacherService.create(dto);
        log.info("Successfully created teacher with ID: {}", response.id());
        return response;
    }

    @PutMapping("/{id}")
    public TeacherResponse updateTeacher(@PathVariable Long id, @RequestBody @Valid UpdateTeacherRequest dto) {
        log.info("Updating teacher with ID: {}", id);
        TeacherResponse response = teacherService.update(id, dto);
        log.info("Updated teacher with ID: {}", id);
        return response;
    }

    @GetMapping("/{id}")
    public TeacherResponse getTeacher(@PathVariable Long id) {
        log.info("Fetching teacher with ID: {}", id);
        return teacherService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        log.warn("Deleting teacher with ID: {}", id);
        teacherService.delete(id);
        log.info("Deleted teacher with ID: {}", id);
    }
}
