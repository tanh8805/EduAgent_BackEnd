package com.eduagent.backend.features.ai.service;

import com.eduagent.backend.features.ai.dto.MessageDTO;
import com.eduagent.backend.features.ai.entity.ChatSession;
import com.eduagent.backend.features.ai.exception.SessionIDNotFound;
import com.eduagent.backend.features.ai.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIChatOrchestrationService {

  private final ChatService chatService;
  private final ChatSessionRepository chatSessionRepository;

  public Integer prepareSession(Integer sessionId) {
    if (sessionId == null) {
      ChatSession newSession = ChatSession.builder().title("Đoạn chat mới").build();
      return chatSessionRepository.save(newSession).getId();
    }
    if (!chatSessionRepository.existsById(sessionId)) {
      throw new SessionIDNotFound("Session ID không tồn tại!");
    }
    return sessionId;
  }

  public List<MessageDTO> buildConversationPayload(Integer sessionId, String userPrompt) {
    chatService.saveMessage(sessionId, "USER", userPrompt);

    List<MessageDTO> historyWindow = chatService.getChatHistoryWindow(sessionId, 10);

    List<MessageDTO> finalPayload = new ArrayList<>();
    finalPayload.add(new MessageDTO("system", "Bạn là một trợ lý giáo dục thông minh thuộc dự án EduAgent."));
    finalPayload.addAll(historyWindow);

    return finalPayload;
  }
}