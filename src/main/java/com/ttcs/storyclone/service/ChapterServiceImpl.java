package com.ttcs.storyclone.service;

import com.ttcs.storyclone.entity.Chapter;
import com.ttcs.storyclone.entity.Story;
import com.ttcs.storyclone.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    private ChapterRepository chapterRepository;

    @Autowired
    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Override
    public List<Chapter> findByStory(Story story) {
        return chapterRepository.findByStory(story);
    }

    @Override
    public Chapter findById(Long id) {
        Optional<Chapter> result = chapterRepository.findById(id);
        Chapter chapter = null;
        if (result.isPresent()) {
            chapter = result.get();
        } else {
            throw new RuntimeException("Chapter not found");
        }
        return chapter;
    }

    @Override
    public Chapter findByChapterNumberAndStoryId(Long chapterNumber, Long storyId) {
        Optional<Chapter> result = Optional.ofNullable(chapterRepository.findByChapterNumberAndStoryId(chapterNumber, storyId));
        Chapter chapter = null;
        if (result.isPresent()) {
            chapter = result.get();
        } else {
            throw new RuntimeException("Chapter not found");
        }
        return chapter;
    }
}
