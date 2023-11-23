package com.school.courseregistration.service;

import com.school.courseregistration.entity.Student;

public interface StudentService {

    Student getStudent(Long id);
    Student getStudent(String username);


}
