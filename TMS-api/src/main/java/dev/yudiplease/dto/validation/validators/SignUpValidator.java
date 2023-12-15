package dev.yudiplease.dto.validation.validators;

import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.validation.constraints.SignUpConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class SignUpValidator implements ConstraintValidator<SignUpConstraint, SignUpRequest> {
    private static final String MESSAGE_USERNAME = "Поле Имя пользователя не может быть пустым.";
    private static final String MESSAGE_EMAIL = "Поле Почта пользователя не может быть пустым.";
    private static final String MESSAGE_PASSWORD = "Поле Пароль не может быть пустым.";

    @Override
    @SneakyThrows
    public boolean isValid(SignUpRequest request, ConstraintValidatorContext context) {
        boolean valid = true;
        if (Objects.isNull(request.getUsername()) || !StringUtils.hasText(request.getUsername())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_USERNAME, "username");
        }
        if (Objects.isNull(request.getEmail()) || !StringUtils.hasText(request.getEmail())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_EMAIL, "email");
        }
        if (Objects.isNull(request.getPassword()) || !StringUtils.hasText(request.getPassword())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_PASSWORD, "password");
        }
        return valid;
    }

    private void buildConstraintViolationWithTemplate(ConstraintValidatorContext context, String message, String fieldName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
