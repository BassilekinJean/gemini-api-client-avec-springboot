package com.ia.gemini.bassilekin_inf222.gemini_chatbot.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ia.gemini.bassilekin_inf222.gemini_chatbot.service.GeminiDirectApiService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final GeminiDirectApiService geminiDirectApiService; // Change le type de service

    public ChatController(GeminiDirectApiService geminiDirectApiService) { // Change l'injection
        this.geminiDirectApiService = geminiDirectApiService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatResponse processChatMessage(@RequestBody userMessage request) {
        System.out.println("Message reçu du user : " + request.getMessage());

        // Appelle la méthode du nouveau service Gemini
        String botResponse = geminiDirectApiService.getGeminiResponse(request.getMessage());

        return new ChatResponse(botResponse);
    }

    @NoArgsConstructor
    public static class userMessage {

        private String message;

        public String getMessage(){
            return this.message;
        }

        public void setMessage(String meko) {
            this.message = meko;
        }

    }

    public static class ChatResponse {
        private String response;
        public ChatResponse() {}
        public ChatResponse(String response) { this.response = response; }
        public String getResponse() { return response; }
        public void setResponse(String response) { this.response = response; }
    }

}