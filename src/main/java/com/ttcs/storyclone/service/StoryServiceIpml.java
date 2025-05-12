package com.ttcs.storyclone.service;

import com.ttcs.storyclone.repository.StoryRepository;
import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.model.StoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceIpml implements StoryService {

    private StoryRepository storyRepository;

    @Autowired
    public StoryServiceIpml(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story findById(Long id) {
        Optional<Story> result = storyRepository.findById(id);

        Story story = null;

        if (result.isPresent()) {
            story = result.get();
        } else {
            throw new RuntimeException("Story not found");
        }
        return story;
    }

    @Override
    public List<Story> findAll() {
        return storyRepository.findAll();
    }

    @Override
    public List<Story> findByStatus(StoryStatus status) {
        return storyRepository.findByStatus(status);
    }

    @Override
    public List<Story> findTop10CompletedStories() {
        return storyRepository.findTop10ByStatusOrderByCreatedAtDesc(StoryStatus.completed);
    }

    @Override
    public List<Story> findTop10ByOrderByUpdatedAtDesc() {
        return storyRepository.findTop10ByOrderByUpdatedAtDesc();
    }

    @Override
    public Story save(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public void delete(Story story) {
        storyRepository.delete(story);
    }

    @Override
    public void deleteById(Long id) {
        Story story = findById(id);
        storyRepository.delete(story);
    }
    @Override
    public long getTotalStories() {
        return storyRepository.count();
    }
}
