package com.ttcs.storyclone.controller;

import com.ttcs.storyclone.model.Genre;
import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.service.GenreService;
import com.ttcs.storyclone.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private GenreService genreService;
    private StoryService storyService;

    @Autowired
    public HomeController(GenreService genreService, StoryService storyService) {
        this.genreService = genreService;
        this.storyService = storyService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        List<Story> stories = storyService.findAll();
        model.addAttribute("stories", stories);
        List<Story> top10LatestStories = storyService.findTop10ByOrderByUpdatedAtDesc();
        model.addAttribute("top10LatestStories", top10LatestStories);
        List<Story> top10CompletedStories = storyService.findTop10CompletedStories();
        model.addAttribute("top10CompletedStories", top10CompletedStories);
        return "index";
    }

}
