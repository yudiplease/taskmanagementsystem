package dev.yudiplease.service.impl;

import dev.yudiplease.config.security.BaseUserDetails;
import dev.yudiplease.dto.requests.SignInRequest;
import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.responses.JwtAuthResponse;
import dev.yudiplease.entity.User;
import dev.yudiplease.exception.UserNotFoundException;
import dev.yudiplease.repository.UserRepository;
import dev.yudiplease.service.AuthService;
import dev.yudiplease.service.JwtService;
import dev.yudiplease.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.yudiplease.entity.Role.USER;
import static dev.yudiplease.entity.EntityStatus.CONFIRMED;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;

    @Override
    public JwtAuthResponse signUp(SignUpRequest request) {
        User user = mapper.toEntity(request);
        user.setId(UUID.randomUUID());
        user.setHashPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(USER);
        user.setEntityStatus(CONFIRMED);
        repository.save(user);
        var jwt = jwtService.generateToken(new BaseUserDetails(user));
        return JwtAuthResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthResponse signIn(SignInRequest request) {
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(), new BaseUserDetails(user).getAuthorities()));
        var jwt = jwtService.generateToken(new BaseUserDetails(user));
        return JwtAuthResponse.builder().token(jwt).build();
    }
}
