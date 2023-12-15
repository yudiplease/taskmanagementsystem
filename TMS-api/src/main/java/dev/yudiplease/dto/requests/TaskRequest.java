package dev.yudiplease.dto.requests;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import dev.yudiplease.dto.validation.constraints.TaskConstraint;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TaskConstraint
@Builder
public class TaskRequest {
    private String title;
    private String description;
    private String taskStatus;
    private String taskPriority;
    private String performerMail;
}
