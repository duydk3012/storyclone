package com.ttcs.storyclone.controller;

import com.ttcs.storyclone.model.Story;
import com.ttcs.storyclone.model.Chapter;
import com.ttcs.storyclone.model.Genre;
import com.ttcs.storyclone.service.StoryService;
import com.ttcs.storyclone.service.ChapterService;
import com.ttcs.storyclone.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private GenreService genreService;

    // Dashboard
    @GetMapping("")
    public String adminDashboard(Model model) {
        model.addAttribute("totalStories", storyService.getTotalStories());
        model.addAttribute("totalChapters", chapterService.getTotalChapters());
        return "admin/admin-dashboard";
    }

    // Story Management
    @GetMapping("/stories")
    public String manageStories(Model model) {
        model.addAttribute("stories", storyService.findAll());
        return "admin/story/story-list";
    }

    @GetMapping("/stories/add")
    public String showAddStoryForm(Model model) {
        model.addAttribute("story", new Story());
        model.addAttribute("genres", genreService.findAll());
        return "admin/story/story-form";
    }

    @GetMapping("/stories/edit/{id}")
    public String showEditStoryForm(@PathVariable Long id, Model model) {
        Story story = storyService.findById(id);
        model.addAttribute("story", story);
        model.addAttribute("genres", genreService.findAll());
        return "admin/story/story-form";
    }

    @PostMapping("/stories/save")
    public String saveStory(@ModelAttribute Story story) {
        storyService.save(story);
        return "redirect:/admin/stories";
    }

    @GetMapping("/stories/delete/{id}")
    public String deleteStory(@PathVariable Long id) {
        storyService.deleteById(id);
        return "redirect:/admin/stories";
    }

    // Chapter Management
    @GetMapping("/stories/{storyId}/chapters")
    public String manageChapters(@PathVariable Long storyId, Model model) {
        Story story = storyService.findById(storyId);
        model.addAttribute("story", story);
        model.addAttribute("chapters", chapterService.findByStoryId(storyId));
        return "admin/chapter/chapter-list";
    }

    @GetMapping("/stories/{storyId}/chapters/add")
    public String showAddChapterForm(@PathVariable Long storyId, Model model) {
        Story story = storyService.findById(storyId);
        Chapter chapter = new Chapter();
        chapter.setStory(story);
        model.addAttribute("chapter", chapter);
        return "admin/chapter/chapter-form";
    }

    @GetMapping("/chapters/edit/{id}")
    public String showEditChapterForm(@PathVariable Long id, Model model) {
        Chapter chapter = chapterService.findById(id);
        model.addAttribute("chapter", chapter);
        return "admin/chapter/chapter-form";
    }

    @PostMapping("/chapters/save")
    public String saveChapter(@ModelAttribute Chapter chapter) {
        chapterService.save(chapter);
        return "redirect:/admin/stories/" + chapter.getStory().getId() + "/chapters";
    }

    @GetMapping("/chapters/delete/{id}")
    public String deleteChapter(@PathVariable Long id) {
        Chapter chapter = chapterService.findById(id);
        Long storyId = chapter.getStory().getId();
        chapterService.deleteById(id);
        return "redirect:/admin/stories/" + storyId + "/chapters";
    }
}
