package com.ttcs.storyclone.repository;

import com.ttcs.storyclone.entity.Chapter;
import com.ttcs.storyclone.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByStory(Story story);
    Chapter findByChapterNumberAndStoryId(Long chapterNumber, Long storyId);
}
