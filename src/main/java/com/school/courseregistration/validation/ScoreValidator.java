package com.school.courseregistration.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class ScoreValidator implements ConstraintValidator<Score, String> {

    List<String> scores = List.of("A", "B", "C", "D", "F");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        for (String score : scores) {
            if (value.equals(score)) return true;
        }
        return false;
    }
}
