package dev.yudiplease.controller;

import dev.yudiplease.api.AuthApi;
import dev.yudiplease.dto.requests.SignInRequest;
import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.responses.JwtAuthResponse;
import dev.yudiplease.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public JwtAuthResponse signUp(SignUpRequest request) {
        return authService.signUp(request);
    }

    @Override
    public JwtAuthResponse signIn(SignInRequest request) {
        return authService.signIn(request);
    }
}
