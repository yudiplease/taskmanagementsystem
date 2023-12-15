package dev.yudiplease.controller;

import dev.yudiplease.api.TaskApi;
import dev.yudiplease.dto.requests.CommentaryRequest;
import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.responses.CommentaryResponse;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.entity.Commentary;
import dev.yudiplease.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService service;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        return service.createTask(request);
    }

    @Override
    public TaskResponse getTaskById(UUID taskId) {
        return service.getTaskById(taskId);
    }

    @Override
    public List<TaskResponse> getAll(String status, String priority) {
        if (status == null && priority == null) {
            return service.getAllTasks();
        } else if (status == null) {
            return service.getTasksByPriority(priority);
        } else {
            return service.getTasksByStatus(status);
        }
    }


//    @Override
//    public List<TaskResponse> getTasksByPriority(String priority) {
//        return service.getTasksByPriority(priority);
//    }

    @Override
    public TaskResponse updateTask(UUID taskId, TaskRequest request) {
        return service.updateTask(taskId, request);
    }

    @Override
    public void deleteTaskById(UUID taskId) {
        service.deleteTaskById(taskId);
    }

    @Override
    public List<CommentaryResponse> getAllCommentsByTaskId(UUID taskId, int page, int size) {
        return service.getAllCommentsByTaskId(taskId, page, size);
    }

    @Override
    public CommentaryResponse createCommentary(UUID taskId, CommentaryRequest request) {
        return service.createCommentary(taskId, request);
    }
}
