package com.school.courseregistration.service;

import com.school.courseregistration.entity.Student;
import com.school.courseregistration.excepetion.EntityNotFoundException;
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
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student);
    }

    @Override
    public Student getStudent(String username) {
        Optional<Student> student = studentRepository.findByUserName(username);
        return unwrapStudent(student);
    }

    static Student unwrapStudent(Optional<Student> entity) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(Student.class);
    }


}
