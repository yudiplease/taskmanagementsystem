package dev.yudiplease.exception;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(UUID userId) {
        super(String.format("User with id = %s not found", userId));
    }

    public UserNotFoundException(String username) {
        super(String.format("User with mail = %s not found", username));
    }
}
