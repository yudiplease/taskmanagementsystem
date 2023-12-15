package dev.yudiplease.service;

import dev.yudiplease.dto.requests.SignInRequest;
import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.responses.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);
    JwtAuthResponse signIn(SignInRequest request);
}
