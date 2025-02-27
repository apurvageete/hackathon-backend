package com.summary.codeGist.Controller;

import com.summary.codeGist.Service.ChatContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatContentController {
    private  ChatContentService chatContentService;

    public ChatContentController(ChatContentService chatContentService) {
        this.chatContentService = chatContentService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> handleFormData(@RequestParam Map<String, String> formData,
                                                 @RequestHeader("Authorization") String token) {
        return chatContentService.processFormData(formData, token);
    }
}
