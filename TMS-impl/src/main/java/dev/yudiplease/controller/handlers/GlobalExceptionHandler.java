package dev.yudiplease.controller.handlers;

import dev.yudiplease.dto.responses.ResponseErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String SECURITY_EXCEPTION_MESSAGE = "Access denied for reason: %s";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionMessage> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionMessage.builder()
                        .message(String.format(SECURITY_EXCEPTION_MESSAGE, "Authentication failure"))
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionMessage> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionMessage.builder()
                        .message(String.format(SECURITY_EXCEPTION_MESSAGE, "Not authorized"))
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionMessage> handleAuthException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<ExceptionMessage> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseErrorMessage.builder()
                        .errors(ex.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> new ResponseErrorMessage.Error(fieldError.getField(),
                                        fieldError.getCode(), fieldError.getDefaultMessage()))
                                .collect(Collectors.toList()))
                        .build());
    }
}
