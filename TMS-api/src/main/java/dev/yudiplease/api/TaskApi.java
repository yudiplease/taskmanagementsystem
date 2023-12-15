package dev.yudiplease.api;

import dev.yudiplease.dto.requests.CommentaryRequest;
import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.responses.CommentaryResponse;
import dev.yudiplease.dto.responses.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Задачи | Tasks", description = "Задача")
@Validated
@RequestMapping("/api/v1/tasks/")
public interface TaskApi {
    @Operation(summary = "Создание задачи", operationId = "create-task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача создана"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")})
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse createTask(@Valid @RequestBody TaskRequest request);

    @Operation(summary = "Получение задачи по ID", operationId = "get-task-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача получена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")})
    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse getTaskById(@PathVariable UUID taskId);

    @Operation(summary = "Получение списка задач", operationId = "get-tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач получен")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<TaskResponse> getAll(@RequestParam(value = "status", required = false) String status,
                              @RequestParam(value = "priority", required = false) String priority);

    @Operation(summary = "Обновление задачи", operationId = "update-task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача обновлена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")})
    @PutMapping("{taskId}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse updateTask(@PathVariable UUID taskId, @Valid @RequestBody TaskRequest request);

    @Operation(summary = "Удаление задачи", operationId = "delete-task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача удалена"),
            @ApiResponse(responseCode = "400", description = "Задача не найдена")})
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTaskById(@PathVariable UUID taskId);

    @Operation(summary = "Получение списка комментариев к задаче", operationId = "get-commentaries-by-task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список комментариев получен")})
    @GetMapping("{taskId}/comments")
    @ResponseStatus(HttpStatus.OK)
    List<CommentaryResponse> getAllCommentsByTaskId(@PathVariable UUID taskId,
                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size);

    @Operation(summary = "Создание нового комментария к задаче", operationId = "create-commentary-to-task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Комментарий создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")})
    @PostMapping("/{taskId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    CommentaryResponse createCommentary(@PathVariable UUID taskId, @RequestBody CommentaryRequest request);
}