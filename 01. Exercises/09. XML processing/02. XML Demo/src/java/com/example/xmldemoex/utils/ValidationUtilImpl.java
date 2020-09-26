package com.example.xmldemoex.utils;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        return this.validator.validate(entity);
    }
}
