package dev.yudiplease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CurrentTimestamp
    private LocalDateTime createdAt;

    @CurrentTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;
}

