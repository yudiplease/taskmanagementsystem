package dev.yudiplease.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ResponseErrorMessage {
    private final Long timestamp = Instant.now().getEpochSecond();
    private final List<Error> errors;

    @AllArgsConstructor
    @Getter
    public static class Error {
        private final String fieldName;
        private final String code;
        private final String detailMessage;
    }
}
