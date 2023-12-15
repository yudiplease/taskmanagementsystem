package dev.yudiplease.dto.requests;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String username;
    private String email;
}
