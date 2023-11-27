package com.school.courseregistration.excepetion;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entity) {
        super("The "+ entity.getSimpleName().toLowerCase() +" does not exist in our records");
    }

}
