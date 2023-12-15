package dev.yudiplease.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class User extends AbstractEntity {
    private String username;
    private String hashPassword;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Task> createdTasks;
}
