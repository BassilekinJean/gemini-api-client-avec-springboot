package com.ia.gemini.bassilekin_inf222.gemini_chatbot.service;

// This code snippet demonstrates how to generate text using the Google GenAI API.
// src/main/java/com/ia/gemini/bassilekin_inf222/gemini_chatbot/entities/GeminiDirectApiService.java

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy; // Pour fermer le client proprement

@Service
public class GeminiDirectApiService {

    private final Client geminiClient;
    private final String geminiModelName;

    public GeminiDirectApiService(
            @Value("${spring.ai.openai.api-key}") String apiKey,
            @Value("${spring.ai.openai.chat.options.model}") String modelName) {
        this.geminiClient = new Client.Builder().apiKey(apiKey).build();
        this.geminiModelName = modelName;
    }

    public String getGeminiResponse(String userMessage) {
        try {
            GenerateContentResponse response =
                    geminiClient.models.generateContent(
                            geminiModelName, // Le modèle configuré
                            userMessage,     // Le message de l'utilisateur
                            null);           // Pas d'options supplémentaires pour l'instant

            // Gère le cas où la réponse texte est nulle ou vide
            if (response != null && response.text() != null && !response.text().isEmpty()) {
                return response.text();
            } else {
                System.err.println("La réponse de Gemini est vide ou nulle.");
                return "Désolé, je n'ai pas pu générer de réponse pour le moment (réponse vide).";
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel direct à l'API Gemini: " + e.getMessage());
            e.printStackTrace(); // Affiche la stack trace pour un meilleur débogage
            return "Désolé, une erreur est survenue lors de la communication avec Gemini.";
        }
    }

    /**
     * Ferme le client Gemini proprement lorsque l'application Spring Boot s'arrête.
     */
    @PreDestroy
    public void closeGeminiClient() {
        if (geminiClient != null) {
            geminiClient.close();
            System.out.println("Client Gemini fermé.");
        }
    }
}