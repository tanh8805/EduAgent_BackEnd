package com.eduagent.backend.features.ai.controller;

import com.eduagent.backend.features.ai.dto.AIRequestDTO;
import com.eduagent.backend.features.ai.dto.AIResponseDTO;
import com.eduagent.backend.features.ai.dto.MessageDTO;
import com.eduagent.backend.features.ai.factory.AIServiceFactory;
import com.eduagent.backend.features.ai.service.AIChatOrchestrationService;
import com.eduagent.backend.features.ai.service.AIService;
import com.eduagent.backend.features.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {
  private final AIServiceFactory aiServiceFactory;
  private final AIChatOrchestrationService aiChatOrchestrationService;
  private final ChatService chatService;

  @PostMapping("/ask")
  public ResponseEntity<?> askAi(@RequestBody AIRequestDTO requestDTO) {
    Integer sessionId = aiChatOrchestrationService.prepareSession(requestDTO.getSessionId());
    List<MessageDTO> conversationPayload = aiChatOrchestrationService.buildConversationPayload(sessionId, requestDTO.getPrompt());
    AIService aiService = aiServiceFactory.getAIService(requestDTO.getProvider());
    String aiResponse = aiService.generateText(conversationPayload);
    chatService.saveMessage(sessionId, "AI", aiResponse);
    AIResponseDTO res = new AIResponseDTO(sessionId, aiResponse);
    return ResponseEntity.ok(res);
  }
}
