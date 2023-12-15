package dev.yudiplease;

import dev.yudiplease.config.security.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class})
public class TaskManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystemApplication.class, args);
    }
}
