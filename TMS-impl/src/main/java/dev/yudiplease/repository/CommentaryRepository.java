package dev.yudiplease.repository;

import dev.yudiplease.entity.Commentary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, UUID> {
    List<Commentary> findAllByTask_Id(UUID taskId);

    Page<Commentary> findAllByTask_Id(UUID taskId, Pageable pageable);
}