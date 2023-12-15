package dev.yudiplease.utils.mappers;

import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.requests.TaskRequest;
import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.TaskResponse;
import dev.yudiplease.dto.responses.UserResponse;
import dev.yudiplease.entity.Task;
import dev.yudiplease.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest request);

    TaskResponse toDto(Task task);

    List<Task> toEntityList(List<TaskRequest> tasks);

    List<TaskResponse> toDtoList(List<Task> tasks);
}
