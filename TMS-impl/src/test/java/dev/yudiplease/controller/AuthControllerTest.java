package dev.yudiplease.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yudiplease.config.security.filters.JwtAuthFilter;
import dev.yudiplease.dto.requests.SignInRequest;
import dev.yudiplease.dto.requests.SignUpRequest;
import dev.yudiplease.dto.responses.JwtAuthResponse;
import dev.yudiplease.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @Mock
    private AuthService authService;

    @Test
    public void testSignIn() throws Exception {
        SignInRequest signInRequest = new SignInRequest("john@example.com", "password");
        JwtAuthResponse authResponse = new JwtAuthResponse("token");

        when(authService.signIn(signInRequest)).thenReturn(authResponse);
        JwtAuthResponse actualResponse = authController.signIn(signInRequest);
        assertEquals(authResponse, actualResponse);
    }

    @Test
    public void testSignUp() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("test", "test@test.test", "12345");
        JwtAuthResponse expectedResponse = new JwtAuthResponse("token");

        when(authService.signUp(signUpRequest)).thenReturn(expectedResponse);
        JwtAuthResponse actualResponse = authController.signUp(signUpRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}