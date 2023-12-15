package dev.yudiplease.dto.requests;

import dev.yudiplease.dto.validation.constraints.SignUpConstraint;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SignUpConstraint
public class SignUpRequest {
    private String username;
    @Email
    private String email;
    private String password;
}