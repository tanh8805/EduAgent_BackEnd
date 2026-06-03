package com.eduagent.backend.features.ai.service.impl;

import com.eduagent.backend.features.ai.dto.GeminiResponseDTO;
import com.eduagent.backend.features.ai.dto.MessageDTO;
import com.eduagent.backend.features.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service("GEMINI")
@RequiredArgsConstructor
public class GeminiServiceImpl implements AIService {

  private final WebClient webClient;

  @Override
  public String generateText(List<MessageDTO> prompt) {
    Map<String, Object> body = Map.of(
            "model", "gemini/gemini-3.1-flash-lite-preview",
            "stream", false,
            "messages", prompt
    );

    try {
      GeminiResponseDTO response = webClient.post()
              .uri("http://localhost:20128/v1/chat/completions")
              .header("Content-Type", "application/json")
              .bodyValue(body)
              .retrieve()
              .bodyToMono(GeminiResponseDTO.class)
              .block();
      if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
        return response.getChoices().get(0).getMessage().getContent();
      }
      return "Failed to receive a valid response from the AI";
    }
    catch (Exception e) {
      e.printStackTrace();
      return "9ROUTER ERROR: " + e.getMessage();
    }
  }
}