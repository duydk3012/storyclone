package com.ttcs.storyclone.service;

import com.ttcs.storyclone.model.Chapter;
import com.ttcs.storyclone.model.Story;

import java.util.List;

public interface ChapterService {
    List<Chapter> findAll();
    List<Chapter> findByStory(Story story);
    Chapter findById(Long id);
    Chapter findByChapterNumberAndStoryId(Long chapterNumber, Long storyId);
    List<Chapter> findByStoryId(Long storyId);
    Chapter save(Chapter chapter);
    void delete(Chapter chapter);
    void deleteById(Long id);
    long getTotalChapters();
}
