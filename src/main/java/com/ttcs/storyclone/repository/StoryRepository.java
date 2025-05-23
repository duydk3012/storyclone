package com.ttcs.storyclone.repository;

import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.model.StoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findByStatus(StoryStatus status);
    List<Story> findTop10ByStatusOrderByCreatedAtDesc(StoryStatus status);
    List<Story> findTop10ByOrderByUpdatedAtDesc();
    long count();
}
