package dev.yudiplease.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class Commentary extends AbstractEntity {

    @ManyToOne
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Task task;

    private String body;
}
