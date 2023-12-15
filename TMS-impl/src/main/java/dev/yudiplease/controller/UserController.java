package dev.yudiplease.controller;

import dev.yudiplease.api.UserApi;
import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.dto.responses.UserResponse;
import dev.yudiplease.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public UserResponse getUserById(UUID userId) {
        return service.getUserById(userId);
    }

    @Override
    public List<UserResponse> getUsers() {
        return service.getUsers();
    }

    @Override
    public UserResponse updateUser(UUID userId, UserRequest request) {
        return service.updateUserById(userId, request);
    }

    @Override
    public void deleteUserById(UUID userId) {
        service.deleteUserById(userId);
    }

    @Override
    public List<TaskResponse> getTasksByUserId(UUID userId) {
        return service.getTasksByAuthor(userId);
    }

}
