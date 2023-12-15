package dev.yudiplease.dto.validation.validators;

import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.validation.constraints.TaskConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class TaskValidator implements ConstraintValidator<TaskConstraint, TaskRequest> {

    private static final String MESSAGE_TITLE= "Заголовок задачи не может быть пустым.";
    private static final String MESSAGE_DESCRIPTION= "Описание задачи не может быть пустым.";
    private static final String MESSAGE_STATUS_BLANK = "Статус задачи не может быть пустым.";

    private static final String MESSAGE_STATUS_REGEXP = "Статус задачи может принимать только следующие значения: " +
            "IN_WAITING - В ожидании; " +
            "IN_PROCESS - В процессе; " +
            "FINISHED - Завершено; " +
            "CLOSED - Закрыто; " +
            "CANCELLED - Отменено.";

    private static final String MESSAGE_PRIORITY_REGEXP = "Приоритет задачи может принимать только следующие значения: " +
            "LOW - Низкий; " +
            "MEDIUM - Средний; " +
            "HIGH - Высокий.";

    private static final String MESSAGE_PRIORITY= "Приоритет задачи не может быть пустым.";
    private static final String MESSAGE_PERFORMER= "Исполнитель задачи не может быть пустым.";


    @Override
    @SneakyThrows
    public boolean isValid(TaskRequest request, ConstraintValidatorContext context) {
        boolean valid = true;
        if (isNull(request.getTitle()) || !hasText(request.getTitle())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_TITLE, "title");
        }
        if (isNull(request.getDescription()) || !hasText(request.getDescription())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_DESCRIPTION, "description");
        }
        if (isNull(request.getTaskStatus()) || !hasText(request.getTaskStatus())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_STATUS_BLANK, "taskStatus");
        }
        if (!request.getTaskStatus().matches("^(IN_WAITING|IN_PROCESS|FINISHED|CLOSED|CANCELLED)$")) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_STATUS_REGEXP, "taskStatus");
        }
        if (!request.getTaskPriority().matches("^(LOW|MEDIUM|HIGH)$")) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_PRIORITY_REGEXP, "taskPriority");
        }
        if (isNull(request.getTaskPriority()) || !hasText(request.getTaskPriority())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_PRIORITY, "taskPriority");
        }
        if (isNull(request.getPerformerMail()) || !hasText(request.getPerformerMail())) {
            valid = false;
            buildConstraintViolationWithTemplate(context, MESSAGE_PERFORMER, "performerMail");
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
