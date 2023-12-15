package dev.yudiplease.dto.validation.constraints;

import dev.yudiplease.dto.validation.validators.TaskValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TaskValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface TaskConstraint {
    String message() default "{TaskConstraint error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
