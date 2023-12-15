package dev.yudiplease.utils.mappers;

import dev.yudiplease.dto.requests.CommentaryRequest;
import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.CommentaryResponse;
import dev.yudiplease.dto.responses.UserResponse;
import dev.yudiplease.entity.Commentary;
import dev.yudiplease.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentaryMapper {
    Commentary toEntity(CommentaryRequest request);

    CommentaryResponse toDto(Commentary commentary);

    List<CommentaryResponse> toDtoList(List<Commentary> users);
}
