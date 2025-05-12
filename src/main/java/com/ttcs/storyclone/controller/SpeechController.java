package com.ttcs.storyclone.controller;

import com.ttcs.storyclone.service.GoogleTtsService; // Import the new service
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException; // Import IOException

@Controller
public class SpeechController {
    private static final Logger logger = LoggerFactory.getLogger(SpeechController.class);
    private final GoogleTtsService googleTtsService;

    @Autowired
    public SpeechController(GoogleTtsService googleTtsService) {
        this.googleTtsService = googleTtsService;
    }

    @GetMapping("/tts")
    public String showTtsPage(Model model) {
        model.addAttribute("text", "");
        logger.debug("Rendering TTS page");
        return "tts";
    }

    @PostMapping(value = "/tts/speak", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] speak(@RequestParam("text") String text) {
        logger.debug("Received TTS request with text: {}", text);
        if (text == null || text.trim().isEmpty()) {
            logger.warn("Empty or null text received for TTS");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Văn bản không được để trống");
        }

        try {
            logger.info("Calling Google Cloud TTS API with text length: {}", text.length());
            byte[] audioData = googleTtsService.synthesizeText(text);

            if (audioData == null || audioData.length == 0) {
                logger.error("Google Cloud TTS returned no audio data");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Không nhận được dữ liệu âm thanh từ Google Cloud");
            }
            logger.info("Successfully generated audio via Google Cloud, length: {}", audioData.length);
            return audioData;
        } catch (Exception e) { // General exception handling for unexpected issues
            logger.error("An unexpected error occurred during speech generation: {}", e.getMessage(), e);
            String errorMessage = "Lỗi không mong muốn khi tạo giọng nói: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, e);
        }
    }
}