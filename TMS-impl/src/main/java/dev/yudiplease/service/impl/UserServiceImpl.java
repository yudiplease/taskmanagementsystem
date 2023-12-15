package dev.yudiplease.service.impl;

import dev.yudiplease.config.security.BaseUserDetailsService;
import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.dto.responses.UserResponse;
import dev.yudiplease.entity.EntityStatus;
import dev.yudiplease.entity.Task;
import dev.yudiplease.entity.User;
import dev.yudiplease.exception.UserNotFoundException;
import dev.yudiplease.repository.TaskRepository;
import dev.yudiplease.repository.UserRepository;
import dev.yudiplease.service.UserService;
import dev.yudiplease.utils.mappers.TaskMapper;
import dev.yudiplease.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @Override
    public UserResponse updateUserById(UUID userId, UserRequest request) {
        User user = getByIdOrThrow(userId);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        User user = getByIdOrThrow(userId);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(UUID userId) {
        User user = getByIdOrThrow(userId);
        user.setEntityStatus(EntityStatus.DELETED);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper .toDtoList(users);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new BaseUserDetailsService(userRepository);
    }

    @Override
    public List<TaskResponse> getTasksByAuthor(UUID userId) {
        List<Task> tasks = taskRepository.findAllByAuthor_Id(userId);
        return taskMapper.toDtoList(tasks);
    }

    private User getByIdOrThrow(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User getUser = user.get();
            if (getUser.getEntityStatus().equals(EntityStatus.DELETED)) {
                throw new UserNotFoundException(userId);
            }
            return getUser;
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
