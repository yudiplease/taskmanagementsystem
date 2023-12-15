package dev.yudiplease.dto.validation.constraints;

import dev.yudiplease.dto.validation.validators.SignUpValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = SignUpValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface SignUpConstraint {
    String message() default "{SignUpConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
