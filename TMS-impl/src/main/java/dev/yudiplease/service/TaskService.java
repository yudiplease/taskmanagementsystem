package dev.yudiplease.service;

import dev.yudiplease.dto.requests.CommentaryRequest;
import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.responses.CommentaryResponse;
import dev.yudiplease.dto.responses.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);


    TaskResponse getTaskById(UUID taskId);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(UUID taskId, TaskRequest request);

    void deleteTaskById(UUID taskId);

    List<CommentaryResponse> getAllCommentsByTaskId(UUID taskId, int page, int size);

    CommentaryResponse createCommentary(UUID taskId, CommentaryRequest request);

    List<TaskResponse> getTasksByStatus(String taskStatus);

    List<TaskResponse> getTasksByPriority(String taskStatus);
}
