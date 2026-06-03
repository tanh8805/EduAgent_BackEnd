package com.eduagent.backend.features.ai.service;

import com.eduagent.backend.features.ai.dto.MessageDTO;
import com.eduagent.backend.features.ai.entity.ChatMessage;
import com.eduagent.backend.features.ai.repository.ChatMessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
  ChatMessage saveMessage(Integer sessionId, String sender, String text);
  List<MessageDTO> getChatHistoryWindow(Integer sessionId, int windowSize);
}
