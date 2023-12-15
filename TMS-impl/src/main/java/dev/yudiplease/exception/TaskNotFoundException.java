package dev.yudiplease.exception;

import java.util.UUID;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(UUID taskId) {
        super(String.format("Task with id = %s not found", taskId));
    }
}
