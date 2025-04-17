package com.ttcs.storyclone.service;

import com.ttcs.storyclone.entity.Chapter;
import com.ttcs.storyclone.entity.Story;

import java.util.List;

public interface ChapterService {
    List<Chapter> findByStory(Story story);
    Chapter findById(Long id);
    Chapter findByChapterNumberAndStoryId(Long chapterNumber, Long storyId);
}
