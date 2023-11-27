package com.school.courseregistration.security.manager;

import com.school.courseregistration.entity.Student;
import com.school.courseregistration.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private StudentService studentServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        Student student = studentServiceImpl.getStudent(authentication.getName());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), student.getPassword())) {
            throw new BadCredentialsException("You provided an Incorrect Password, Try Again!!");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), student.getPassword());
    }
}
