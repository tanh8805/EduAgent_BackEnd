package com.eduagent.backend.features.ai.controller;

import com.eduagent.backend.features.ai.dto.AIRequestDTO;
import com.eduagent.backend.features.ai.dto.AIResponseDTO;
import com.eduagent.backend.features.ai.factory.AIServiceFactory;
import com.eduagent.backend.features.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {
  private final AIServiceFactory aiServiceFactory;

  @PostMapping("/ask")
  public ResponseEntity<?> askAi(@RequestBody AIRequestDTO requestDTO) {
    AIService aiService = aiServiceFactory.getAIService(requestDTO.getProvider());

    String response = aiService.generateText(requestDTO.getPrompt());
    AIResponseDTO res =  new AIResponseDTO(response);
    return ResponseEntity.status(200).body(res);
  }
}
