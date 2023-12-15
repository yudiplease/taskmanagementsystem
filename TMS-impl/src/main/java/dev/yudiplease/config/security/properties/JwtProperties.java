package dev.yudiplease.config.security.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "jwt")
@Validated
@Data
public class JwtProperties {
    @NotNull
    private String secretKey;
}
