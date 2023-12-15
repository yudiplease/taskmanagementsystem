package dev.yudiplease.api;

import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.dto.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users | Пользователи", description = "Пользователь")
@Validated
@RequestMapping("/api/v1/users")
public interface UserApi {

    @Operation(summary = "Получение пользователя по ID", operationId = "get-user-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь получен"),
            @ApiResponse(responseCode = "400", description = "Пользователь не найден")})
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse getUserById(@PathVariable UUID userId);

    @Operation(summary = "Получение списка пользователей", operationId = "get-all-users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей получен")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<UserResponse> getUsers();

    @Operation(summary = "Обновление пользователя", operationId = "update-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь обновлён"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")})
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@PathVariable UUID userId, UserRequest request);

    @Operation(summary = "Удаление пользователя", operationId = "delete-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удалён"),
            @ApiResponse(responseCode = "400", description = "Пользователь не найден")})
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUserById(@PathVariable UUID userId);

    @Operation(summary = "Получение списка задач по автору", operationId = "get-all-tasks-by-author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач получен")})
    @GetMapping("/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    List<TaskResponse> getTasksByUserId(@PathVariable UUID userId);

}