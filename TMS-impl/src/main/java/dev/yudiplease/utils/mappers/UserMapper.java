package dev.yudiplease.utils.mappers;

import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.requests.UserRequest;
import dev.yudiplease.dto.responses.UserResponse;
import dev.yudiplease.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);

    User toEntity(SignUpRequest request);

    UserResponse toDto(User user);

    List<UserResponse> toDtoList(List<User> users);
}
