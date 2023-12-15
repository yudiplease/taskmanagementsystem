package dev.yudiplease.controller;

import dev.yudiplease.dto.requests.CommentaryRequest;
import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.responses.CommentaryResponse;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.dto.responses.UserResponse;
import dev.yudiplease.entity.Commentary;
import dev.yudiplease.entity.Task;
import dev.yudiplease.entity.User;
import dev.yudiplease.service.TaskService;
import dev.yudiplease.utils.mappers.CommentaryMapper;
import dev.yudiplease.utils.mappers.TaskMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService service;

    @Mock
    private TaskMapper mapper;

    @Mock
    private CommentaryMapper commentaryMapper;

    @Test
    public void testCreateTask() {
        TaskRequest taskRequest = new TaskRequest("title", "description", "IN_WAITING", "LOW", "test@test.test");
        UserResponse author = new UserResponse("username", "password");
        UserResponse executor = new UserResponse("username", "test@test.test");
        TaskResponse taskResponse = new TaskResponse("title", "description", "IN_WAITING", "LOW", author, executor);

        when(service.createTask(taskRequest)).thenReturn(taskResponse);
        TaskResponse actualResponse = taskController.createTask(taskRequest);
        assertEquals(taskResponse, actualResponse);
    }

    @Test
    public void testGetTaskById() {
        UUID id = UUID.randomUUID();
        Task task = Task.builder()
                .id(id)
                .build();
        TaskResponse expectedResponse = mapper.toDto(task);
        when(service.getTaskById(id)).thenReturn(expectedResponse);
        TaskResponse actualResponse = taskController.getTaskById(id);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testUpdateTask() {
        UUID id = UUID.randomUUID();
        TaskRequest taskRequest = TaskRequest.builder()
                .title("newTitle")
                .description("description")
                .build();
        Task task = Task.builder()
                .id(id)
                .title("title")
                .description("description")
                .build();
        task.setTitle(taskRequest.getTitle());
        TaskResponse expectedResponse = mapper.toDto(task);

        when(service.updateTask(id, taskRequest)).thenReturn(expectedResponse);
        TaskResponse actualResponse = taskController.updateTask(id, taskRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testCreateCommentary() {
        UUID id = UUID.randomUUID();
        CommentaryRequest commentaryRequest = CommentaryRequest.builder()
                .body("Test Commentary")
                .build();
        Commentary commentary = Commentary.builder()
                .body("Test Commentary")
                .build();
        CommentaryResponse expectedResponse = commentaryMapper.toDto(commentary);
        when(service.createCommentary(id, commentaryRequest)).thenReturn(expectedResponse);
        CommentaryResponse actualResponse = taskController.createCommentary(id, commentaryRequest);
        assertEquals(expectedResponse, actualResponse);
    }
}
