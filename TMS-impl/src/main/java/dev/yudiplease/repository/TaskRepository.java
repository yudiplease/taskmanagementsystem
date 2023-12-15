package dev.yudiplease.repository;

import dev.yudiplease.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByAuthor_Id(UUID authorId);
    List<Task> findAllByTaskStatus(TaskStatus taskStatus);
    List<Task> findAllByTaskPriority(TaskPriority taskPriority);
}
