package dev.yudiplease.api;

import dev.yudiplease.dto.requests.SignInRequest;
import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.responses.JwtAuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Authentication | Аутентификация", description = "Аутентификация")
@RequestMapping("/api/v1/auth")
public interface AuthApi {
    @Operation(summary = "Создание пользователя", operationId = "create-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")})
    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    JwtAuthResponse signUp(@Valid @RequestBody SignUpRequest request);

    @Operation(summary = "Авторизация пользователя", operationId = "auth-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь авторизован"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")})
    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    JwtAuthResponse signIn(@Valid @RequestBody SignInRequest request);

}
