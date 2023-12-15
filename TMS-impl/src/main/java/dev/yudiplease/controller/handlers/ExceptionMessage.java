package dev.yudiplease.controller.handlers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {
    private String message;

    private String exceptionName;
}
