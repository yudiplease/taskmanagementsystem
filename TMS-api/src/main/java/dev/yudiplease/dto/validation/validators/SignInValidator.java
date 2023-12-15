package dev.yudiplease.dto.validation.validators;

import dev.yudiplease.dto.requests.SignInRequest;
import dev.yudiplease.dto.validation.constraints.SignInConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class SignInValidator implements ConstraintValidator<SignInConstraint, SignInRequest> {
    private static final String MESSAGE_MAIL = "Поле Почта не может быть пустым.";
    private static final String MESSAGE_PASSWORD = "Поле Пароль не может быть пустым.";

    @Override
    @SneakyThrows
    public boolean isValid(SignInRequest request, ConstraintValidatorContext context) {
        boolean valid = true;
        if (isNull(request.getEmail()) || !hasText(request.getEmail())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_MAIL, "username");
        }
        if (isNull(request.getPassword()) || !hasText(request.getPassword())) {
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
