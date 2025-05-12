package com.ttcs.storyclone.controller;


import com.ttcs.storyclone.model.Chapter;
import com.ttcs.storyclone.model.Genre;
import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.service.ChapterService;
import com.ttcs.storyclone.service.GenreService;
import com.ttcs.storyclone.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stories/{storyId}/chapters")
public class ChapterController {
    private ChapterService chapterService;
    private GenreService genreService;
    private StoryService storyService;

    @Autowired
    public ChapterController(ChapterService chapterService, GenreService genreService, StoryService storyService) {
        this.chapterService = chapterService;
        this.genreService = genreService;
        this.storyService = storyService;
    }

    @GetMapping("/chapter-{chapterNumber}")
    public String showChapterContent(@PathVariable Long chapterNumber, @PathVariable Long storyId, Model model) {
        Chapter chapter = chapterService.findByChapterNumberAndStoryId(chapterNumber, storyId);
        List<Genre> genres = genreService.findAll();
        Story story = storyService.findById(storyId);
        List<Chapter> chapters = chapterService.findByStory(story);
        model.addAttribute("story", story);
        model.addAttribute("genres", genres);
        model.addAttribute("chapter", chapter);
        model.addAttribute("chapters", chapters);
        return "chapter";
    }
}
