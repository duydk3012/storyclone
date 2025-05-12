package com.ttcs.storyclone.service;

import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.model.StoryStatus;

import java.util.List;

public interface StoryService {
    List<Story> findAll();
    List<Story> findByStatus(StoryStatus status);
    List<Story> findTop10CompletedStories();
    List<Story> findTop10ByOrderByUpdatedAtDesc();
    Story findById(Long id);
    Story save(Story story);
    void delete(Story story);
    void deleteById(Long id);
    long getTotalStories();
}
