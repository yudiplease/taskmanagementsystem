package dev.yudiplease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class Task extends AbstractEntity {
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @OneToMany
    private List<Commentary> commentaries;
}
