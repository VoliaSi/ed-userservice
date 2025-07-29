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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController
{

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    // --- TEACHERS ---

    @PostMapping("/teachers")
    public TeacherResponse createTeacher(@RequestBody CreateTeacherRequest dto)
    {
        Teacher saved = teacherRepository.save(teacherMapper.fromCreateRequest(dto));
        return teacherMapper.toResponse(saved);
    }

    @PutMapping("/teachers/{id}")
    public TeacherResponse updateTeacher(@PathVariable Long id, @RequestBody UpdateTeacherRequest dto)
    {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacherMapper.updateTeacherFromRequest(dto, teacher);
        return teacherMapper.toResponse(teacherRepository.save(teacher));
    }

    @GetMapping("/teachers/{id}")
    public TeacherResponse getTeacher(@PathVariable Long id)
    {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.toResponse(teacher);
    }

    // --- STUDENTS ---

    @PostMapping("/students")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest dto)
    {
        Student saved = studentRepository.save(studentMapper.fromCreateRequest(dto));
        return studentMapper.toResponse(saved);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest dto)
    {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentMapper.updateStudentFromRequest(dto, student);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@PathVariable Long id)
    {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toResponse(student);
    }
}
