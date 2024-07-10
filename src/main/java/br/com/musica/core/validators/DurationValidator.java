package br.com.musica.core.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;

public class DurationValidator implements ConstraintValidator<DurationConstraint, Duration> {
    @Override
    public boolean isValid(Duration value, ConstraintValidatorContext context) {
        return value != null && !value.isNegative() && value.getSeconds() > 0;
    }
}
