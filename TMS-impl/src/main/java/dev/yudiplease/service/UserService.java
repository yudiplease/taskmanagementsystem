package dev.yudiplease.service;

import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.dto.responses.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse updateUserById(UUID userId, UserRequest request);
    UserResponse getUserById(UUID userId);
    void deleteUserById(UUID userId);
    List<UserResponse> getUsers();

    UserDetailsService userDetailsService();

    List<TaskResponse> getTasksByAuthor(UUID userId);
}
