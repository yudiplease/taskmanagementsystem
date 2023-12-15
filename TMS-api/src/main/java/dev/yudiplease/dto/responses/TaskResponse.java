package dev.yudiplease.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private String title;
    private String description;
    private String taskStatus;
    private String taskPriority;
    private UserResponse author;
    private UserResponse executor;
}
