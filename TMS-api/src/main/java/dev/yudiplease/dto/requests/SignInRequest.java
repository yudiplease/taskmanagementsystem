package dev.yudiplease.dto.requests;

import dev.yudiplease.dto.validation.constraints.SignInConstraint;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SignInConstraint
public class SignInRequest {
    @Email
    private String email;
    private String password;
}
