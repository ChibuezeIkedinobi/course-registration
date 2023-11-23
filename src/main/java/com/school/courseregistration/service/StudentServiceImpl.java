package com.school.courseregistration.service;

import com.school.courseregistration.entity.Student;
import com.school.courseregistration.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Student getStudent(Long id) {
        return null;
    }

    @Override
    public Student getStudent(String username) {
        Optional<Student> student =
    }
}
