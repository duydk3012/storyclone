package com.ttcs.storyclone.service;

import com.ttcs.storyclone.model.Chapter;
import com.ttcs.storyclone.model.Story;
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
    public List<Chapter> findAll() {
        return chapterRepository.findAll();
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
    @Override
    public List<Chapter> findByStoryId(Long storyId) {
        return chapterRepository.findByStoryId(storyId);
    }
    @Override
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }
    @Override
    public void delete(Chapter chapter) {
        chapterRepository.delete(chapter);
    }
    @Override
    public void deleteById(Long id) {
        Chapter chapter = findById(id);
        chapterRepository.delete(chapter);
    }
    @Override
    public long getTotalChapters() {
        return chapterRepository.count();
    }
}
