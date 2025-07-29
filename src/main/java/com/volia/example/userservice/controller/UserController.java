package com.volia.example.userservice.controller;

import com.volia.example.userservice.dto.StudentDTO;
import com.volia.example.userservice.dto.TeacherDTO;
import com.volia.example.userservice.mapper.StudentMapper;
import com.volia.example.userservice.mapper.TeacherMapper;
import com.volia.example.userservice.model.Student;
import com.volia.example.userservice.model.Teacher;
import com.volia.example.userservice.repository.StudentRepository;
import com.volia.example.userservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    // --- TEACHERS ---

    @PostMapping("/teachers")
    public TeacherDTO createTeacher(@RequestBody TeacherDTO dto) {
        Teacher teacher = teacherMapper.toEntity(dto);
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }

    @PutMapping("/teachers/{id}")
    public TeacherDTO updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacherMapper.updateTeacherFromDTO(dto, teacher);
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }

    @GetMapping("/teachers/{id}")
    public TeacherDTO getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.toDTO(teacher);
    }

    // --- STUDENTS ---

    @PostMapping("/students")
    public StudentDTO createStudent(@RequestBody StudentDTO dto) {
        Student student = studentMapper.toEntity(dto);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    @PutMapping("/students/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentMapper.updateStudentFromDTO(dto, student);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDTO(student);
    }
}
