package com.ttcs.storyclone.controller;


import com.ttcs.storyclone.model.Chapter;
import com.ttcs.storyclone.model.Genre;
import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.service.ChapterService;
import com.ttcs.storyclone.service.GenreService;
import com.ttcs.storyclone.service.StoryService;
import com.ttcs.storyclone.util.StoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stories")
public class StoryController {

    private StoryService storyService;
    private GenreService genreService;
    private ChapterService chapterService;

    @Autowired
    public StoryController(StoryService storyService, GenreService genreService, ChapterService chapterService) {
        this.storyService = storyService;
        this.genreService = genreService;
        this.chapterService = chapterService;
    }

    @GetMapping("/{id}")
    public String story(@PathVariable Long id, Model model) {
        Story story = storyService.findById(id);
        model.addAttribute("story", story);
        List<Chapter> chapters = chapterService.findByStory(story);
        model.addAttribute("chapters", chapters);
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("StoryUtils", StoryUtils.class);
        return "story";
    }

}