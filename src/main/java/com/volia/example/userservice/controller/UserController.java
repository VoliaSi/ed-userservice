package com.volia.example.userservice.controller;

import com.volia.example.userservice.dto.student.CreateStudentRequest;
import com.volia.example.userservice.dto.student.StudentResponse;
import com.volia.example.userservice.dto.student.UpdateStudentRequest;
import com.volia.example.userservice.dto.teacher.CreateTeacherRequest;
import com.volia.example.userservice.dto.teacher.TeacherResponse;
import com.volia.example.userservice.dto.teacher.UpdateTeacherRequest;
import com.volia.example.userservice.mapper.StudentMapper;
import com.volia.example.userservice.mapper.TeacherMapper;
import com.volia.example.userservice.model.Student;
import com.volia.example.userservice.model.Teacher;
import com.volia.example.userservice.repository.StudentRepository;
import com.volia.example.userservice.repository.TeacherRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

    // --- TEACHERS ---

    @PostMapping("/teachers")
    public TeacherResponse createTeacher(@RequestBody @Valid CreateTeacherRequest dto) {
        Teacher teacher = teacherMapper.fromCreateRequest(dto);
        teacher.setPassword(passwordEncoder.encode(dto.password())); // Encode password
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toResponse(saved);
    }

    @PutMapping("/teachers/{id}")
    public TeacherResponse updateTeacher(@PathVariable Long id, @RequestBody @Valid UpdateTeacherRequest dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacherMapper.updateTeacherFromRequest(dto, teacher);
        return teacherMapper.toResponse(teacherRepository.save(teacher));
    }

    @GetMapping("/teachers/{id}")
    public TeacherResponse getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.toResponse(teacher);
    }

    // --- STUDENTS ---

    @PostMapping("/students")
    public StudentResponse createStudent(@RequestBody @Valid CreateStudentRequest dto) {
        Student student = studentMapper.fromCreateRequest(dto);
        student.setPassword(passwordEncoder.encode(dto.password())); // Encode password
        Student saved = studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody @Valid UpdateStudentRequest dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentMapper.updateStudentFromRequest(dto, student);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toResponse(student);
    }
}
