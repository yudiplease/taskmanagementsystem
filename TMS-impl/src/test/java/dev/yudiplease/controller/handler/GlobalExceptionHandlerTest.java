package dev.yudiplease.controller.handler;

import dev.yudiplease.controller.handlers.ExceptionMessage;
import dev.yudiplease.controller.handlers.GlobalExceptionHandler;
import dev.yudiplease.dto.responses.ResponseErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void handleHttpMessageNotReadableException_shouldReturnBadRequestAndErrorMessage() {
        // given
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Message not readable");

        // when
        ResponseEntity<ExceptionMessage> response = globalExceptionHandler.handleHttpMessageNotReadableException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Message not readable");
        assertThat(response.getBody().getExceptionName()).isEqualTo("HttpMessageNotReadableException");
    }

    @Test
    public void handleBadCredentialsException_shouldReturnUnauthorizedAndErrorMessage() {
        // given
        BadCredentialsException ex = new BadCredentialsException("Invalid credentials");

        // when
        ResponseEntity<ExceptionMessage> response = globalExceptionHandler.handleBadCredentialsException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().getMessage()).isEqualTo("Access denied for reason: Authentication failure");
        assertThat(response.getBody().getExceptionName()).isEqualTo("BadCredentialsException");
    }

    @Test
    public void handleAccessDeniedException_shouldReturnForbiddenAndErrorMessage() {
        // given
        AccessDeniedException ex = new AccessDeniedException("Access denied");

        // when
        ResponseEntity<ExceptionMessage> response = globalExceptionHandler.handleAccessDeniedException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody().getMessage()).isEqualTo("Access denied for reason: Not authorized");
        assertThat(response.getBody().getExceptionName()).isEqualTo("AccessDeniedException");
    }

    @Test
    public void handleExpiredJwtException_shouldReturnForbiddenAndErrorMessage() {
        // given
        ExpiredJwtException ex = new ExpiredJwtException(null, null, "Token expired");

        // when
        ResponseEntity<ExceptionMessage> response = globalExceptionHandler.handleExpiredJwtException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody().getMessage()).isEqualTo("Token expired");
        assertThat(response.getBody().getExceptionName()).isEqualTo("ExpiredJwtException");
    }

    @Test
    public void handleDataIntegrityViolationException_shouldReturnBadRequestAndErrorMessage() {
        // given
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Data integrity violation");

        // when
        ResponseEntity<ExceptionMessage> response = globalExceptionHandler.handleDataIntegrityViolationException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Data integrity violation");
        assertThat(response.getBody().getExceptionName()).isEqualTo("DataIntegrityViolationException");
    }

    @Test
    public void handleMethodArgumentNotValidException_shouldReturnBadRequestAndErrorMessages() {
        //
        List<FieldError> fieldErrors = Arrays.asList(new FieldError("field1", "code1", "message1"),
                new FieldError("field2", "code2", "message2"));
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        given(bindingResult.getFieldErrors()).willReturn(fieldErrors);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        // when
        ResponseEntity<ResponseErrorMessage> response = globalExceptionHandler.handleMethodArgumentNotValidException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors()).hasSize(2);
    }
}
