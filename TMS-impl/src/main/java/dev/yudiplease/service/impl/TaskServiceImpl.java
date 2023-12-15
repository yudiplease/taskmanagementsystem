package dev.yudiplease.service.impl;

import dev.yudiplease.config.security.BaseUserDetails;
import dev.yudiplease.dto.requests.CommentaryRequest;
import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.responses.CommentaryResponse;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.entity.*;
import dev.yudiplease.exception.TaskNotFoundException;
import dev.yudiplease.exception.UserNotFoundException;
import dev.yudiplease.repository.CommentaryRepository;
import dev.yudiplease.repository.TaskRepository;
import dev.yudiplease.repository.UserRepository;
import dev.yudiplease.service.TaskService;
import dev.yudiplease.utils.mappers.CommentaryMapper;
import dev.yudiplease.utils.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static dev.yudiplease.entity.EntityStatus.CREATED;
import static dev.yudiplease.entity.EntityStatus.DELETED;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final CommentaryMapper commentaryMapper;
    private final CommentaryRepository commentaryRepository;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BaseUserDetails user = (BaseUserDetails) authentication.getPrincipal();
        User author = userRepository.findByEmail(user.getUsername()).orElseThrow(() -> new UserNotFoundException(user.getUsername()));
        User performer = userRepository.findByEmail(request.getPerformerMail()).orElseThrow(() -> new UserNotFoundException(request.getPerformerMail()));
        Task task = Task.builder()
                .entityStatus(CREATED)
                .title(request.getTitle())
                .description(request.getDescription())
                .taskStatus(TaskStatus.valueOf(request.getTaskStatus()))
                .taskPriority(TaskPriority.valueOf(request.getTaskPriority()))
                .author(author)
                .executor(performer)
                .build();
        repository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponse getTaskById(UUID taskId) {
        Task task = getByIdOrThrow(taskId);
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = repository.findAll();
        return taskMapper.toDtoList(tasks);
    }

    @Override
    public TaskResponse updateTask(UUID taskId, TaskRequest request) {
        Task task = getByIdOrThrow(taskId);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setTaskPriority(TaskPriority.valueOf(request.getTaskPriority()));
        task.setTaskStatus(TaskStatus.valueOf(request.getTaskStatus()));
        task.setExecutor(userRepository.findByEmail(request.getPerformerMail()).orElseThrow(() -> new UserNotFoundException(request.getPerformerMail())));
        repository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public void deleteTaskById(UUID taskId) {
        Task task = getByIdOrThrow(taskId);
        task.setEntityStatus(DELETED);
        repository.save(task);
    }

    @Override
    public List<CommentaryResponse> getAllCommentsByTaskId(UUID taskId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Commentary> commentaryPage = commentaryRepository.findAllByTask_Id(taskId, pageable);
        List<Commentary> commentaries = commentaryPage.getContent();
        return commentaryMapper.toDtoList(commentaries);
    }

    @Override
    public CommentaryResponse createCommentary(UUID taskId, CommentaryRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BaseUserDetails user = (BaseUserDetails) authentication.getPrincipal();
        User author = userRepository.findByEmail(user.getUsername()).orElseThrow(() -> new UserNotFoundException(user.getUsername()));
        Task task = getByIdOrThrow(taskId);
        Commentary commentary = commentaryMapper.toEntity(request);
        commentary.setEntityStatus(CREATED);
        commentary.setAuthor(author);
        commentary.setTask(task);
        commentaryRepository.save(commentary);
        return commentaryMapper.toDto(commentary);
    }

    @Override
    public List<TaskResponse> getTasksByStatus(String taskStatus) {
        List<Task> tasks = repository.findAllByTaskStatus(TaskStatus.valueOf(taskStatus));
        return taskMapper.toDtoList(tasks);
    }

    @Override
    public List<TaskResponse> getTasksByPriority(String taskPriority) {
        List<Task> tasks = repository.findAllByTaskPriority(TaskPriority.valueOf(taskPriority));
        return taskMapper.toDtoList(tasks);
    }

    private Task getByIdOrThrow(UUID taskId) {
        Optional<Task> task = repository.findById(taskId);
        if (task.isPresent()) {
            Task getTask = task.get();
            if (getTask.getEntityStatus().equals(EntityStatus.DELETED)) {
                throw new TaskNotFoundException(taskId);
            }
            return getTask;
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

}
